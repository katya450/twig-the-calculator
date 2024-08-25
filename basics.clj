(ns basics)

;; This file is almost the same that we used in ClojureBridge, and should cover the basic functions used here.
;; You can evaluate this file and try them in your own REPL!

;;
;; Clojure Syntax
;;

; ---- Comments ----

; either like this, or if you have a function call you wish to comment you can use #_
#_(+ 1 2)

; ---- Numbers ----

1
123
12,5

; ---- Booleans ----

; The only "false" values are false and nil - all other values are logically true.

true
false

; ---- Strings ----

"Just a normal stringy string"


; ---- Keywords ----

:key
:whatever
:this-acts-also-as-a-function!

; ---- Functions and variables ----

; Binding names to variables

(def numero 3)

; Calling Functions

(+ 2 3)

;; same things:
(- (+ 2 2) 3)
(-> (+ 2 2)
    (- 3))

(inc 1)

(defn hey [name]
  (println "Hi" name))

(defn- hey-inside-this-namespace [name]
  (println "Hello" name))

(hey "you")
; Note the implicit return in Clojure!

; Binding names to variables inside functions

(defn hey-you-its-me [name]
  (let [another-name "Katja"
        hardcoded-weather "awesome!"]
    (println "Hi" name ", it's me" another-name "and the weather is" hardcoded-weather)))

;;
;; Let's make our own function
;;

; Tiger 0.9m
; Horse 2.1m
; Giraffe 4.5m
; Calculate the average height these three animals


;;
;; Data Structures:
;;

; ---- Vector ----

(def victor-the-vector [1 33 3444 6 90])

; - count
; - conj
; - first
; - rest
; - nth

; Optional Exercise
;
; Make a vector of the temperatures for the next 7 days in the town where you live.
; Then use the nth function to get the temperature for next Tuesday.

; ---- Map ----

(def mixed-map {:eka          1
                :toka         :kaksi-vain
                :kolmas       ["what do we have here?" "or here?"]
                :nelkkukelkku false})

; keywords as functions?
(get mixed-map :kolmas)
(:kolmas mixed-map)


; - get
; - assoc
; - dissoc
; - merge
; - update

; Optional Exercise
;
; Make a map with your first and last name.
; Ten add your hometown to it using assoc


; ---- Vector of Maps ----

(def media-map [{:title "Best Movie" :type :movie :rating 5}
                {:title "Worst Movie" :type :movie :rating 1}
                {:title "Average Book" :type :book :rating 3}])

; Optional Exercise
;
; Create a vector with a map of your name and home town
; Then use conj to add a map with your neighbour's name and home town


; ---- Nested Maps ----

(def media-map-2 {:best-book {:title "Best Book 1" :type :book :ratings {:given-rating 5
                                                                         :max-rating 5
                                                                         :lowest-rank-ever 4}}
                  :worst-book {:title "Worst Book Eva" :type :book :ratings {:given-rating 1
                                                                             :max-rating 2
                                                                             :lowest-rank-ever 0}}})


; get-in

(get-in media-map-2 [:best-book :ratings :lowest-rank-ever])


;;
;; Flow control
;;

; - if
; - when

(if (= 2 1)
  "certainly is not"
  "right")

(if (< 3 34) "yes it is")
;; if works without else-branch, but linter nags about it as it's not recommended. For those cases, use when:

(when (< 3 34) "oh yes it is")     ; "kyllä on"
(when (< 39 34) "oh yes it is")    ; nil


; case and cond

(defn gief-keys [x]
  (case x
    10 :ten
    20 :twenty
    30 :forty
    :dunno))

(defn conditiony [x]
  (cond
    (= 1 x) "one"
    (boolean? x) x
    (> x 100) "OVER HUNDRED!"
    :else "whatever"))
