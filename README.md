# boids_simulation :bird: :fish: 
#### Have you already implemented the boids simulation algorithm? So now it's my turn!

## What it is? :raising_hand:
Oh, brother, this is just **unrealistically cool algorithm**!  

Its essence is to **imitate flock behavior**.  
That is, having some kind of abstract agents, we can make them move in flocks (like a large group of birds, fish or other animals).  

I had a lot of fun implementing this algorithm. It amazed me how such simple logic could lead to such magic!

## How it works? :hushed:
This algorithm is based on damn simple logic.  

Suppose we have so-called **agents** that have a **location** in two-dimensional space and a **velocity** represented by a vector.  

Now we need to go through each of the agents and apply some set of rules to them:
1. **Cohesion** (*an agent tends to the center of the flock*);
2. **Separation** (*an agent keeps a distance from other agents*);
3. **Alignment** (*an agent tends to move at the same speed as other agents*).

**That's all!**  

How exactly to implement these rules in the code is a secondary issue.  
Most importantly, we have **agents** and a **set of rules** that they must follow.  

If you want to learn more about the **boids simulation algorithm**, I advise you to read the following articles:
* [«The computational beauty of flocking: boids revisited»](https://www.tandfonline.com/doi/full/10.1080/13873950600883485) *by I. Lebar Bajec, N. Zimic & M. Mraz*
* [«Boids - Stanford Computer Science»](https://cs.stanford.edu/people/eroberts/courses/soco/projects/2008-09/modeling-natural-systems/boids.html) *by Timm[ie] Wong*
* [«Simulating Flocking with the Boids Algorithm»](https://medium.com/fragmentblog/simulating-flocking-with-the-boids-algorithm-92aef51b9e00) *by Rohith Vishwajith*
* [«Boids. Background and Update»](https://www.red3d.com/cwr/boids/) *by Craig Reynolds*

## Features :star2:

#### Interaction with the flock
You can simulate the presence of a **predator** with your mouse.  
To do this, **hold down the left mouse button** and, **without releasing**, move it **near the agents**.
<details>
  <summary>spoiler</summary>
  
  ![2022-06-12 21-06-52](https://user-images.githubusercontent.com/56264511/173247065-df014c53-02a3-4f5b-9299-38dc5ba3e4a1.gif)
</details>

## Demo :heart_eyes_cat:
<kbd>
  <a href="https://www.youtube.com/watch?v=nN1hk71E5CY">
    <img src="https://user-images.githubusercontent.com/56264511/173249754-0f692fff-f3cc-4263-9174-95a9d4574839.png">
  </a>
</kbd>
