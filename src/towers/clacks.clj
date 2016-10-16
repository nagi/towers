(ns towers.clacks
  (:require [clojure.set :refer :all]))

(def alphabet
  {\a [1 0 0 0 0 0 1 0]
   \b [1 0 0 1 1 0 0 1]
   \i [0 1 0 0 1 0 0 1]
   \s [1 0 1 1 1 0 0 1]
   \f [1 1 1 0 1 0 1 1]
   \space [0 0 0 1 1 0 0 0]})

(defn random-char []
  (rand-nth (keys alphabet)))

(defn reverse-alphabet []
  (map-invert alphabet))

(defn character->clack [character]
  (alphabet character))

(defn message->clacks [message]
  (map character->clack message))

(defn clack->character [clack]
  ((reverse-alphabet) clack))

(defn clacks->message [clacks]
  (reduce str ""
          (map clack->character clacks)))
