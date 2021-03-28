# Projet_Dev_Mobil

**Partie 1: Inscription**

L’inscription se fait à l’aide de Firebase, nous utilisons donc FireBase Authentification et FireStore DataBase. FireBase Authentification nous sert d’identifiant unique pour l’utilisateur, à l'aide de l’adresse mail et d’un mot de passe, celui-ci génère une clé unique pour chaque utilisateur que nous utiliserons ensuite dans chaque activité pour l’identifier. Nous utilisons aussi cette clé pour identifier l’utilisateur dans la base de données FireStore dans laquelle les autres informations telles que le prénom, le nom, etc, sont stockées.

**Partie 2: Connexion**

Nous commençons par identifier l’utilisateur à l'aide de firebase Auth. Puis nous récupérons le Prénom de l’utilisateur et son score dans FireStore que nous affichons dans la page de choix de jeu.

**Partie 3: Jeu**

Les 3 modes de jeu essentiels sont disponibles. Le bouton du mode chrono est affiché mais aucun code n'est disponible derrière, ce n'est que visuel (nous ne pouvons d'ailleurs pas cliquer dessus). Chaque mode de jeu à donc comme caractéristiques : 

*Facile : *
* Nombre de blocs éclairés au départ : 1 
* Nombre de blocs éclairés pour gagner : 7 
* Poids du monde : 1


*Difficile :* 

* Nombre de blocs éclairés au départ : 3
* Nombre de blocs éclairés pour gagner : 10
* Poids du monde : 1.5 (raison pour laquelle nous mettons le score en double)


*Extrême :*

* Nombre de blocs éclairés au départ : 4
* Nombre de blocs éclairés pour gagner : 12
* Poids du monde : 2


Dès que le nombre de blocs éclairés pour gagner est atteint, nous ajoutons une couleur, jusqu'à un maximum de 10 blocs de couleur différents. 
L'ajout de la couleur se fait par translation circulaire des blocs de couleur déjà présents pour laisser la place à la nouvelle couleur.
