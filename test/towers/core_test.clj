(ns towers.core-test
  (:require [clojure.test :refer :all]
            [clojure.set :refer :all]
            [towers.core :refer :all])

(deftest test-character->clack
  (testing "converts char to clack vector"
    (is (= (towers.clacks/character->clack \a) [1 0 1 0 1 0 1 0]))))

(deftest test-message->clacks
  (testing "converts message to list of clack vectors"
    (is (= (message->clacks "ab")
           '([1 0 1 0 1 0 1 0],[1 0 0 1 1 0 0 1])))))

(deftest test-clack->character
  (testing "converts clack to char"
    (is (= (clack->character [1 0 1 0 1 0 1 0]) \a))))

(deftest test-clacks->message
  (testing "converts list of clack vectors back to strings"
    (is (=
         (clacks->message '([1 0 1 0 1 0 1 0],[1 0 0 1 1 0 0 1]))
         "ab"))))

(deftest test-random-char
  (testing "random known char"
    (is (= (type (random-char)) java.lang.Character))))
