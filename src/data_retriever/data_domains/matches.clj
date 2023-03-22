(ns data-retriever.data-domains.matches
  (:require [data-retriever.request :refer [get-user get-match-ids get-match get-user-by-uuid]]
            [data-retriever.handleDB :refer [insert-data]]))

;; (def metadata-path ["metadata"])
(def participants-path ["metadata" "participants"])
(def match-id-path ["metadata" "matchId"])

(defn get-player-matches [name]
  (let [user (get-user name)
        match-ids (get-match-ids (user "puuid"))]
    (map #(get-match %1) match-ids)))

(defn cache-participants [match] 
  (doseq [participant-uuid (get-in match participants-path)]
         (insert-data (get-user-by-uuid participant-uuid) "users")))

(defn save-player-matches [name]
  (doseq [match (get-player-matches name)]
    (insert-data (assoc match "_id" (get-in match match-id-path)) "matches")
         (cache-participants match))
  )