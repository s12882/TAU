#pragma once
#include "Game.h"


class Map
{
public:
	Map();
	~Map();

	void DrawMap();

private:
		SDL_Rect src, dest;
		SDL_Texture* background;

};

