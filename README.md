# Projet_Dev_Mobil


Partie 1: Inscription

L’inscription se fera à l’aide de Firebase, nous utiliserons FireBase Authentification et FireStore DataBase.
FireBase Authentification nous servira d’identifiant unique pour l’utilisateur, à l'aide de l’adresse Email et d’un mot de passe, celui-ci générera une clé unique pour chaque utilisateur que nous utiliserons dans chaque activité pour l’identifier. Nous utiliserons aussi cette clé pour identifier l’utilisateur dans la base de données FireStore dans lesquelles les autres informations tel que le Prénom, le Nom, etc, seront stockées.

Partie 2: Connexion

Nous commençons par identifier l’utilisateur à l'aide de firebase Auth.
Puis nous récupérons le Prénom de l’utilisateur et son score dans FireStore que nous afficherons dans la page de choix de jeu.

Partie 3: Jeu

Dans ce color memory, seul le premier niveau, le mode "facile" a eu le temps d'être réalisé. Après la définition de nos différentes variables associées aux couleurs, nous mettons en lien les couleurs et des chiffres entre 1 et 4 pour traiter plus facilement les données. La fonction getButton vérifie que le bouton pressé est le bon. S'il l'est, la séquence s'incrémente et une nouvelle couleur s'ajoute, sinon l'utilisateur perd une vie. La séquence va à un maximum de 10 blocs. La fonction button-show permet la réutilisation de code, traitant d'une part "d'allumer" le nouveau bloc et de changer sa couleur une fois pressé par l'utilisateur.
