#include "Collision.h"

//Vertical collision check
 bool  Collision::isVerticalCollision(const SDL_Rect& rectA, const SDL_Rect& rectB) {

	 int leftA, leftB;
	 int rightA, rightB;
	 int topA, topB;
	 int bottomA, bottomB;

	 //Strony objektu A
	 leftA = rectA.x;
	 rightA = rectA.x + rectA.w;
	 topA = rectA.y;
	 bottomA = rectA.y + rectA.h;

	 //Strony objektu B
	 leftB = rectB.x;
	 rightB = rectB.x + rectB.w;
	 topB = rectB.y;
	 bottomB = rectB.y + rectB.h;

	 //Sprawdzamy kolizje stron objektów 
	 if ((leftA >= leftB && rightA <= rightB) || (leftA <= leftB && rightA >= rightB) || (rightA >= leftB && leftA <= leftB) || (leftA <= rightB && rightA >= rightB)) {
		 if ((bottomA >= topB && topA <= topB) || (topA <= bottomB && bottomA >= bottomB)) {
			 return true;
		 }
	 }
	 return false;
}

 //Horizontal collision check
 bool  Collision::isHorizontalCollision(const SDL_Rect& rectA, const SDL_Rect& rectB){

	 int leftA, leftB;
	 int rightA, rightB;
	 int topA, topB;
	 int bottomA, bottomB;

	 leftA = rectA.x;
	 rightA = rectA.x + rectA.w;
	 topA = rectA.y;
	 bottomA = rectA.y + rectA.h;

	 leftB = rectB.x;
	 rightB = rectB.x + rectB.w;
	 topB = rectB.y;
	 bottomB = rectB.y + rectB.h;

	 //Sprawdzamy kolizje stron objektów 
	 if((topA >= topB && bottomA <= bottomB) || (topA <= topB && bottomA >= bottomB) || (bottomA >= topB && topA <= topB) || (topA <= bottomB && bottomA >= bottomB)) {
		 if((rightA >= leftB && leftA <= leftB) || (leftA <= rightB && rightA >= rightB)) {
			 return true;
		 }
	 }
	 return false;
 }
