(ns helper
  (:require [reagent.core :as r]))

;; This file is here to tell more about the used (and useful!) functions for the calculator! :)


;; ---- Reagent atom, r/atom ---- 

;; used for state management. Can be updated with (swap!), reseted with (reset!), and read with @atomi.
;; Note, that the atom is not mutated, it's always a new atom!

(def atomi (r/atom "twigg")) ;; starting value is "twigg"

(swap! atomi str "ing") ;; add "ing" after "twigg". Updating wants a fuction to update atom with!

(println @atomi) ;; print current value, "twigging"

(reset! atomi "are you digging of the twigging?") ;; reset everything, this is now the current value.


;; ---- range ---- 

;; Useful for taking a range of numbers.
;; returns a list of numbers from x until y

(range 1 11)     ; (1 2 3 4 5 6 7 8 9 10)


;; ---- map ---- 

;; Used for transforming data into a different kind, of to loop items.
;; Takes a function and a collection or items, returns a list.

(map inc [1 2 3 4 5])                 ; (2 3 4 5 6)
(map last {:x 1 :y 2 :z 3})           ; (1 2 3)
(map key {:x 1 :y 2 :z 3})            ; (:x :y :z)
(map #(str "numero " %) [1 2 3 4 5])  ; ("numero 1" "numero 2" "numero 3" "numero 4" "numero 5")
(map (fn [nro] (str "numero " nro)) [1 2 3 4 5]) ;; Same as above but longer :)


;; ----   ^{:key something} ---- 
;; Familiar for those who have worked with React: every repeating element needs a unique key. 
;; This is Clojure way of setting that metadata.

;; ---- do ---- 
;; When you need to ask Clojure to do all the things, instead of only returning the last action.
;; Useful when extending if block to do multiple things

;; JavaScript interop! (js/parseFloat)
