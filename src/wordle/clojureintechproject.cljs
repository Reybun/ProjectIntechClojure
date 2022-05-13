(ns wordle.clojureintechproject)

(def board-state (atom []))
(def counter (atom 0))
(def nb-try (atom 0))
(def hidden-word (atom (rand-nth 
  ["boite" "banal" "audio" "cadre" "calme"] ;;possible words
  )))

;;fill the cell
(defn write-letter [cell letter]
  (set! (.-textContent cell) letter))

;;create the empty cell
(defn make-cell []
  (let [cell (js/document.createElement "div")]
    (set! (.-className cell) "cell")
    cell))

(defn bt-reset []
  (let [link (.createElement js/document "a")]
    (.setAttribute link "href" "/")
    (set! (.-innerText link) "Recommencer la partie")
    link))

(defn set-info [message]
  (let [info (.querySelector js/document ".info")]
    (set! (.-innerText info) message)))

(defn info-text []
  (let [info (.createElement js/document "h2")]
    (set! (.-className info) "info")
    (set! (.-innerText info) "Bienvenue sur le jeu de Yann SIMAJCHEL et Sami ANKI \n\n\nRègle : Trouve le mot caché\nVert : Lettre a la bonne place\nBleu: lettre mal placé\nGris : lettre non présente")
    info))

;;create the board
(defn make-board [n]
  (let [board (js/document.createElement "div")]
    (set! (.-className board) "board")
    ;; adding cells
    (doseq [_ (range n)]
      (let [cell (make-cell)]
        (swap! board-state conj cell)
        (.appendChild board cell)))
    board))

(defn get-letter [cell]
  (.-textContent cell))

;;give color the the cell of id x
(defn color-cell [idx cell]
  (let [color (fn [el color]
                (set! (-> el .-style .-backgroundColor)
                      color))
        letter (get-letter cell)]
    (cond
      (= letter (nth @hidden-word idx))
      (color cell "green")

      (contains? (set @hidden-word) letter)
      (color cell "blue")

      :else
      (color cell "#333333"))))

(defn check-solution [cells]
  (doseq [[idx cell] (map-indexed vector cells)]
    (color-cell idx cell))
  (= (mapv get-letter cells)
     (vec @hidden-word)))

(defn user-input [key]
  (let [start (* 5 @nb-try)
        end (* 5 (inc @nb-try))]
    (cond
      (and (re-matches #"[a-z]" key)
           (< @counter end))
      (do
        (write-letter (nth @board-state @counter) key)
        (swap! counter inc))

      (and (= key "backspace")
           (> @counter start))
      (do
        (swap! counter dec)
        (write-letter (nth @board-state @counter) ""))

      (and (= key "enter")
           (= @counter end))
      (do
        (when (check-solution (subvec @board-state start end))
          (js/alert "Tu as gagné!!!"))
        (cond
          (< @nb-try 5) (set-info (str "Nombre d'essais = " @nb-try))
          :else (set-info (str "Perdu, le mot a trouvé était : " @hidden-word))  
        )
        (swap! nb-try inc))
        

      )))

(defonce listener (atom nil))

(defn ^:dev/before-load unmount []
  (js/document.removeEventListener "keydown" @listener)
  (let [app (js/document.getElementById "app")]
    (set! (.-innerHTML app) "")))

(defn mount []
  (let [app (js/document.getElementById "app")
        bt (bt-reset) 
        info (info-text)
        board (make-board 30)
        input-listener
        (fn [e]
          (user-input (.toLowerCase(.-key e))))]  
    (.appendChild app board)
    (.appendChild app info)
    (.appendChild app bt)
    (reset! listener input-listener)
    (js/document.addEventListener
     "keydown"
     input-listener)))

(mount)