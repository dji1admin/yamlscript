; Copyright 2023 Ingy dot Net
; This code is licensed under MIT license (See License for details)

(ns yamlscript.parser-test
  (:use yamlscript.debug)
  (:require
   [clojure.string :as str]
   [yamlscript.parser :as parser]
   [yamlscript.test :as test]))

(def test-files
  ["test/compiler-stack.yaml"])

(test/remove-tests)

(doseq [test-file test-files]
  (test/load-yaml-tests
   {:yaml-file test-file
    :pick-func #(test/has-keys? [:yamlscript :parse] %)
    :test-func (fn [test]
                 (->> test
                      :yamlscript
                      parser/parse
                      (map pr-str)
                      (map #(subs % 4 (dec (count %))))))
    :want-func (fn [test]
                 (->> test
                      :parse
                      str/split-lines))}))

(comment
  *file*
  *compile-path*
  )
