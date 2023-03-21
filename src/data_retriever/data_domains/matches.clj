(ns data-retriever.data-domains.matches
  (:require [data-retriever.request :refer [get-user get-match-ids get-match]]
            [data-retriever.handleDB :refer [insert-data]]))

(defn get-player-matches [name]
  (let [user (get-user name)
        match-ids (get-match-ids (user "puuid"))]
    (map #(get-match %1) match-ids)))

(defn save-player-matches [name]
  (doseq [match (get-player-matches name)]
         (insert-data match "matches")) 
  )