#include "Map.h"
#include <SDL.h>
#include "TextureLoader.h"

Map::Map()
{
	background = TextureLoader::LoadTexture("Res/Background_0.png");

	src.x = src.y = 0;
	src.w = dest.w = 1280;
	src.h = dest.h = 720;
	dest.x = dest.y = 0;
}


Map::~Map()
{
	SDL_DestroyTexture(background);
}

void Map::DrawMap() {
	TextureLoader::Draw(background, src, dest);
}

