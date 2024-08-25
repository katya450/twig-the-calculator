(ns calculator.core
  (:require [clojure.string :as str]
            [reagent.core :as r]
            [reagent.dom :as d]))


;; -------------------------
;; Atoms (= states)

(def calculation (r/atom 0))

(def last-selected-number (r/atom ""))

(def last-selected-action (r/atom ""))

(def last-click (r/atom "number")) ;; "number" | "action"

(def calculation-in-progress (r/atom false))


;; -------------------------
;; Actions

(defn do-calculate [action number]
  (let [number-as-float (js/parseFloat number)]
    (if (false? @calculation-in-progress)
      (do
        (reset! calculation-in-progress true)
        (reset! calculation number-as-float))
      (do
        (case action
          "+" (reset! calculation (+ @calculation number-as-float))
          "-" (reset! calculation (- @calculation number-as-float))
          "*" (reset! calculation (* @calculation number-as-float))
          "/" (reset! calculation (/ @calculation number-as-float))
          "=" (do (do-calculate @last-selected-action number-as-float)
                  (reset! last-selected-action "")))
        (reset! last-selected-number @calculation)))))


(defn handle-decimal []
  (when-not (str/includes? @last-selected-number ".")
    (reset! last-selected-number (str @last-selected-number "."))))


(defn handle-number-click [number]
  (if (= @last-click "number")
    (swap! last-selected-number str number)
    (reset! last-selected-number number))
  (reset! last-click "number"))


(defn handle-action-click [action]
  (if (= @last-click "number")
    (do (do-calculate @last-selected-action @last-selected-number)
        (reset! last-selected-action action))
    (reset! last-selected-action action))
  (reset! last-click "action"))


;; -------------------------
;; Views

(defn display []
  [:input {:class "result-view"
           :type :text
           :disabled true
           :placeholder "feed me numbers"
           :value @last-selected-number}])

(defn decimal-button []
  ^{:key ","}
  [:button {:class "action-button"
            :on-click #(handle-decimal)} ","])

(defn neg-pos-button []
  ^{:key "neg"}
  [:button {:class "action-button-2"
            :on-click #(reset! last-selected-number (* @last-selected-number -1))} "+/-"])

(defn percent-button []
  ^{:key "%"}
  [:button {:class "action-button-2"
            :on-click #()} "%"]
  )

(defn clear-button []
  ^{:key "clear"}
  [:button {:class "clear-button"
            :on-click #(do (reset! calculation 0)
                           (reset! last-selected-number ""))} "C"])

(defn action-button [action]
  ^{:key action}
  [:button {:class "action-button"
            :on-click #(handle-action-click (str action))} action])

(defn number-button [number]
  ^{:key number}
  [:button {:class "number-button"
            :on-click #(handle-number-click number)}
   number])

(defn calculator []
  [:div {:class "grid"}
   (display)
   (clear-button)
   (neg-pos-button)
   (percent-button)
   (map number-button (range 7 10))
   (action-button "+")
   (map number-button (range 4 7))
   (action-button "-")
   (map number-button (range 1 4))
   (action-button "*")
   (number-button 0)
   (decimal-button)
   (action-button "/")
   (action-button "=")])

(defn home-page []
  [:div
   [:div [:h2 "Clojure(Script) Calculator"]]
   [calculator]])


;; -------------------------
;; Initialize app

(defn mount-root []
  (d/render [home-page] (.getElementById js/document "app")))

(defn ^:export init! []
  (mount-root))
