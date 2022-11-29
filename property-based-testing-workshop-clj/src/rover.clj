(ns rover)

(def initial {:direction :N :location [0 0]})

(defn move-forward [{direction :direction [x y] :location}]
  (let [new-location (condp = direction
                       :N [(inc x) y]
                       :S [(dec x) y]
                       :E [x (inc y)]
                       :W [x (dec y)])]
    {:direction direction :location new-location}))

(defn move-backward [{direction :direction [x y] :location}]
  (let [new-location (condp = direction
                       :N [(dec x) y]
                       :S [(inc x) y]
                       :E [x (dec y)]
                       :W [x (inc y)])]
    {:direction direction :location new-location}))

(defn rotate-left [{direction :direction location :location}]
  (let [new-direction (condp = direction
                       :N :W
                       :S :E
                       :E :N
                       :W :S)]
    {:direction new-direction :location location}))

(defn rotate-right [{direction :direction location :location}]
  (let [new-direction (condp = direction
                        :N :E
                        :S :W
                        :E :S
                        :W :N)]
    {:direction new-direction :location location}))

(def commands-by-id {:F move-forward
                     :B move-backward
                     :L rotate-left
                     :R rotate-right})

(defn execute-commands [rover cmds]
  (reduce (fn [r cmd] (cmd r))
    rover
    (map commands-by-id cmds)))

(comment
  ;; rovers are specified by a direction and a location
  (def a-rover {:direction :N :location [1 0]})

  ;; forms in a comment block can be easily sent to the repl
  (= (move-forward initial) {:direction :N :location [1 0]})
  (= (execute-commands initial (list :F :B :L :L :L :L)) initial)

  ,,,)
