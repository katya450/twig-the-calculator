(ns calculator.core
    (:require
      [reagent.core :as r]
      [reagent.dom :as d]))

;; -------------------------
;; Actions


(defn handle-number-click [number]
  (println "oh but the number was" number))

;; -------------------------
;; Views

(defn display []
  [:input {:class "result-view"
           :type :text
           :disabled true
           :placeholder "feed me numbers"}])


(defn action-button [action]
  ^{:key action}
  [:button {:class "action-button"} action])


(defn number-button [number]
  ^{:key number}
  [:button {:class "number-button"
            :on-click #(handle-number-click (str number))}
   number])


(defn calculator []
  [:div {:class "grid"}
   (display)
   (map number-button (range 7 10))
   (action-button "+")
   (map number-button (range 4 7))
   (action-button "-")
   (map number-button (range 1 4))
   (action-button "*")
   (number-button 0)
   (action-button ",")
   (action-button "/")
   (action-button "=")]) 
    
;; TODO: explain range

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
