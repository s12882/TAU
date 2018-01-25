#pragma once
#include "SDL.h"


class Collision
{
public:

	static bool isVerticalCollision(const SDL_Rect& rectA, const SDL_Rect& rectB);

	static bool isHorizontalCollision(const SDL_Rect& rectA, const SDL_Rect& rectB);
private:

};


