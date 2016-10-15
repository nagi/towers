(ns towers.core
  (:require [quil.core :as q]
            [quil.middleware :as m]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def alphabet
  {\a [1 0 0 0 0 0 1 0]
   \b [1 0 0 1 1 0 0 1]
   \i [0 1 0 0 1 0 0 1]
   \s [1 0 0 1 1 0 0 1]
   \f [1 1 1 0 1 0 1 1]
   \space [0 0 0 1 1 0 0 0]})

(defn random-char []
  (rand-nth (keys alphabet)))

(defn reverse-alphabet []
  (clojure.set/map-invert alphabet))

(defn character->clack [character]
  (alphabet character))

(defn message->clacks [message]
  (map character->clack message))

(defn clack->character [clack]
  ((reverse-alphabet) clack))

(defn clacks->message [clacks]
  (reduce str ""
          (map clack->character clacks)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(def tower (atom [0 0 0 1 1 0 0 0]))

(defn change-tower! []
  (swap! tower (fn [x] (character->clack (random-char)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn setup []
  ; Set frame rate to 30 frames per second.
  (q/frame-rate 3)
  ; Set color mode to HSB (HSV) instead of default RGB.
  (q/color-mode :hsb)
  ; setup function returns initial state.
  [0 0 1 0 1 0 0 0])

(defn update-state [state]
  (character->clack (random-char)))

(defn draw-state [state]
  ; Clear the sketch by filling it with light-grey color.
  (q/background 250)
  ; Set circle color.
  (q/fill 40 255 255)

  ; Calculate x and y coordinates of the circle.
  (println state)

  (dotimes [n 8]
     (when (= (get state n) 1) (q/ellipse 50 (+ 50 (* n 50)) 50 50))))




(q/defsketch towers
  :title "You spin my circle right round"
  :size [500 500]
                                        ; setup function called only once, during sketch initialization.
  :setup setup
  ; update-state is called on each iteration before draw-state.
  :update update-state
  :draw draw-state
  :features [:keep-on-top]
  ; This sketch uses functional-mode middleware.
  ; Check quil wiki for more info about middlewares and particularly
  ; fun-mode.
  :middleware [m/fun-mode])
 
