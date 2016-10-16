(ns towers.core
  (:require [quil.core :as q]
            [quil.middleware :as m]
            [towers.clacks :refer :all]))

(defn go []
  (println "Close the Applet window to quit."))

(defn setup []
  ; Set frame rate to 30 frames per second.
  (q/frame-rate 3)
  ; Set color mode to HSB (HSV) instead of default RGB.
  (q/color-mode :hsb)
  ; setup function returns initial state.
  (repeat 8 [0 0 0 0 0 0 0 0]))

(defn update-state [state]
  "First tower gets a random clack message"
  (pop (into [(character->clack (random-char))] state)))

(defn draw-state [state]
  ; Clear the sketch by filling it with light-grey color.
  (q/background 250)
  ; Set circle color.
  (q/fill 40 255 255)

  ; Draw Towers
  (dotimes [t 8] ;; each tower
    (dotimes [b 8]  ;; each bit
      (when (= (get (get state t) b) 1) (q/ellipse (+ 50 (* t 50)) (+ 50 (* b 50)) 50 50)))))

(q/defsketch towers
  :title "Clack Tower Simulation"
  :size [450 450]
  ; setup function called only once, during sketch initialization.
  :setup setup
  ; update-state is called on each iteration before draw-state.
  :update update-state
  :draw draw-state
  :features [:keep-on-top]
  ; This sketch uses functional-mode middleware.
  :middleware [m/fun-mode])
