(ns data-retriever.core
  (:gen-class)
  (:require [data-retriever.request :refer [get-match]]
             [data-retriever.handleDB :refer [insert-data]]))
(def match-id "BR1_2698211752")
(def match (get-match match-id))
(println match)
(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "\n\n")
  (println "Match!")
  (insert-data match "Matches"))
