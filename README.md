


# Game

As part of my University module project, I have been tasked to develop a game, which involves building a functional 2D Game using the City Engine Physics library. 
<br><br>

I have recreated a simpler version of the 3DS Mario Kart, which has similar functions of collecting items, defeating enemies and reaching an end-goal in order to win the game. 




## Character Design
I have found this sprite design on Pinterest. The reason why I have decided to use it is because it matches the late night city aesthetic of my game. The purpose of the game is to enable the user to have a relaxed playing session, whilst also enjoying defeating the enemies and collecting objects. This game has a competitive edge, hwoever it is against ourselves, rather than others. This is because the high score only records the individual user's information.
<br>
<br>
![normalPosition](https://github.com/deffonotfari/2DGameProject/assets/105233354/3c1fc5c3-5c8b-4fd2-aeae-65f9c79a48a6)
<img width="60" position = "center" alt="normalPosition" src="https://github.com/deffonotfari/2DGameProject/assets/105233354/3c1fc5c3-5c8b-4fd2-aeae-65f9c79a48a6">

## Levels

<p><b>Level 1:</b></p>
<br>
<img width="761" alt="Screenshot 2024-05-07 at 03 09 38" src="https://github.com/deffonotfari/2DGameProject/assets/105233354/f54051ba-5526-45fc-8643-3891d38422d1">
<br>
<br>
<p><b>Level 2:</b></p>
<br>
<img width="762" alt="Screenshot 2024-05-07 at 03 11 08" src="https://github.com/deffonotfari/2DGameProject/assets/105233354/ea9e7aeb-784a-41ac-81ff-cd036d537e36">
<br>
<br>


<p><b>Level 3:</b></p>
<br>
<img width="735" alt="Screenshot 2024-05-07 at 03 11 51" src="https://github.com/deffonotfari/2DGameProject/assets/105233354/a5addbca-6661-4105-a6f2-de0901815f0b">
<br>
<br>

<p><b>Level 4:</b></p>
<br>
<img width="803" alt="Screenshot 2024-05-07 at 03 12 05" src="https://github.com/deffonotfari/2DGameProject/assets/105233354/e306161e-a634-4419-bd28-2dbcf1d16291">
<br>
<br>

## Features
These are the following features of my game:
- There are multiple levels, which are subclasses of a common class
- Implementation of GUI to enable level navigation
    - A pop-up screen containing the game's instruction
    - Pause and resume feature
    - Mute and unmute feature
    - Allow user to adjust background track's volume
    - Enable user to quit game
- Enable player to collect various different forms of collectibles (i.e. Coins, Cookies, Teleportation Power Up)
- Sound effects and background tracks are implemented
- Implementation of collision events - reaction for when the player collides with another object in the game
- High score pop-up: the user's score is shown, along side with the top 3 highest scores
- Player statistics are displayed
    - Remaining lives
    - How many coins have been collected
    - How many good cookies have been collected
    - Current score
    - How many tickets have been awareded to the player for defeating enemies 
- I have implemented multiple timers with different functionalities
    - I have included a timer for when the player dies, so when all of the lives of the player has been lost, the explosion occurs after a set period of time. 
    - Change to dark figure's behaviour after 3 shots
    - Good cookie or bad cookie is spawned periodically until the max limit for that level is reached
    - Cannon explosions - the image changes from cannon to explosion after it collides with something after a set period of time
    - There's a period of time between when the game ends and when the high score pop-up is displayed
- High score system
    - current score is displayed
    - Top 3 scores of all time is displayed
    - Enable user to exit game or restart from level 1
- I have implemneted enemies within my game, that the player has to defeat
    - Included:
        - patrolling enemy (i.e. the ghost)
        - an enemy that follows the main player ( i.e. the dark figure)
        - an enemy that shoots (i.e. the tank)
- The player can shoot a projectile in both direction.
- The cannon, which is shot by the tank, is an example of the Implementation of an anti-gravitational projectile
## Color Reference
These are the colours which I have used within the game

| Color             | Hex                                                                |
| ----------------- | ------------------------------------------------------------------ |
| Black | ![#00000](https://via.placeholder.com/10/00000?text=+) #00000 |
| Russian Violet| ![#35234d](https://via.placeholder.com/10/35234d?text=+) #35234d |
| Purpureus | ![#904eac](https://via.placeholder.com/10/904eac?text=+) #904eac |
| Space Cadet | ![#161c37](https://via.placeholder.com/10/161c37?text=+) #161c37 |
| Tropical Indigo | ![#8a8ad1](https://via.placeholder.com/10/8a8ad1?text=+) #8a8ad1 |
| Sandy Brown| ![#f2a65e](https://via.placeholder.com/10/f2a65e?text=+) #f2a65e |

## Acknowledgements
 - [EZGIF](https://ezgif.com/maker): enabling me to create an animation of my sprites moving
 - [RemoveBG](https://www.remove.bg/): This website removes image background easily
 - [Free Sound](https://freesound.org/): I have utilised this website to find various sound effects
 - [PNG HUT](https://pnghut.com/png/mmiRKTSMP1/ghost-pixel-art-gif-image-technology-cute-transparent-png/): Enemy Pixel 
 - [Level 1 Audio](https://youtu.be/SMflLu0x-Ss?si=xbkVTp11T_GAm5Ld): Background Audio for Level 1
 - [Level 2 Audio](https://youtu.be/jUhsDT63JB8?si=kU3pCrP6R_Qs7eJl): Background Audio for Level 2
  - [Level 3 Audio](https://youtu.be/I6a5wTZyfQE?si=LeBRGGy3Xbsvbe8j): Background Audio for Level 3
  - [Level 4 Audio](https://youtu.be/pZcsUHiL-bs?si=SGH734WMxREpZgps): Background Audio for Level 4
 



## Feedback
If you have any feedback, please reach out to me at alamfariha560@gmail.com


## Authors

- [@deffonotfari](https://github.com/deffonotfari)

