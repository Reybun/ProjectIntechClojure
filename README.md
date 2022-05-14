# clojureintechproject

## Description

Un jeu motus où le but est de trouver le mot choisi aléatoirement de 5 lettres en 6 essais.
En validant le mot un code couleur aidera le jour à trouver la solution
Si la lettre est :
- verte : Lettre a la bonne place
- bleu: lettre mal placée
- grise : le mot ne contient pas cette lettre
A la fin de la partie en cas de défaite nous affichons le mot qui était à trouver.

## Comment jouer

Pour jouer écrivez un mot avec votre clavier et valider avec la touche "Entrée".
Il est possible de d'effacer une lettre avec la touche "Backspace/Retour".
Il est possible de relancer la partie en cliquant sur le boutton en bas de l'écran.
Suivez vos essais et retrouvez les règles en jeu directement en ligne.

## Pour faire fonctionné

Nécessite Clojure d'installer

Lancer `npm i` pour installer les dépendances

pour lancer au choix : 

```
npm run dev
```
ou

```
npx shadow-cljs watch main
```

Attendre de voir  "HTTP server available at http://localhost:8000"

Le projet est maintenant accessible:
    URL : http://localhost:8000/

code clojure dans 
```
src/motus/clojureintechproject.cljs
```

## Les éléments avancés

Nous avons utilisé divers éléments avancés comme : 
- Clojure Script (fonction graphique) : Qui nous sert à créer les éléments du DOM pour l'affichage web
- Application web : Application web pour rendre le jeu plus interactif
- Shadow-cljs : Nous permet de compiler facilement le code Clojure Script
- Gestion d'événements : Gestion de l'input utilisateur avec un event listener
- Logique de jeu et conditionnelle : En récupérant l'input utilisateur, nous vérifions que l'input est bien une lettre pour l'afficher, sinon si l'input est un Retour nous effaçons la lettre précédemment rentrée, Si l'input est Entré nous vérifions si le mot à trouver est égal au mot essayé.


Enjoy!
Contactez nous en cas de problèmes : 
- Yann Simajchel  : simajchel@et.intechinfo.fr
- Sami Anki  : anki@et.intechinfo.fr
