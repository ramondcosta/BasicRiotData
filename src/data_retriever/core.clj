(ns data-retriever.core
  (:gen-class)
  (:require [data-retriever.request :refer [get-user]]
            [data-retriever.handleDB :refer [insert-data]]
            [data-retriever.setup :refer [setup!]]))
;; (def match-id "BR1_2698384278")
;; (def match (get-match match-id))

(defn -main
  [& args]
  (println "\n\n")
  (println "started!")
  (println "\n\n")
  (setup!))
