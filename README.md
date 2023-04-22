# MiningIUT
Plugin de crafts et d'items spéciaux de l'IUT info de Lille

## Informations :

 - Spigot Version : 1.19.4
 - Plugin et permissions testé sur Paper 1.19.4 (#516), LuckPem 5.4.71


## Fichiers :

 - lang.yml : Gère les messages d'erreur et l'affichage
 - config.yml : Gère les paramètres du plugin 
 
## Commandes :
 
 - /xpbottle [joueur nombre] : pour se donner une bouteille ou un nombre x de bouteille à un joueur
 
## Permissions :
 
 - xpbottle.xpbottle : /xpbottle
 
## Fichier par défaut :

<details>
  <summary>lang.yml</summary>

  ```
  prefix: ''
  error-prefix: '&4[&cErreur&4]&c'
  no-permission: Vous n'avez pas la permission de faire cette commande !
  no-value: Informations manquante, merci d'utiliser la commande correctement !
  no-player: Le joueur n'est pas connecte
  not-enough-xp: Vous n'avez pas assez d'éxpérience pour faire cela
  max-entity: Le nombre d'entité à était atteinte dans le chunk
  ```
</details>

<details>
  <summary>config.yml</summary>

  ```
  # Max entity by chunk
  MaxEntityByChunk: 256
  ```
</details>
