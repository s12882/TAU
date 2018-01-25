#include "TextureLoader.h"
#include <stdio.h>
#include <SDL_image.h>
#include <iostream>
#include "Game.h"

using namespace std;

//https://github.com/carlbirch/BirchEngine/blob/master/BirchEngine/Src/TextureManager.cpp
SDL_Texture* TextureLoader::LoadTexture(const char* file) {

	SDL_Surface* tmpSurface = IMG_Load(file);
	SDL_Texture* texture = SDL_CreateTextureFromSurface(Game::renderer, tmpSurface);
	SDL_FreeSurface(tmpSurface);

	return texture;
}

void TextureLoader::Draw(SDL_Texture * texture, SDL_Rect src, SDL_Rect dest)
{
	SDL_RenderCopy(Game::renderer, texture, &src, &dest);
}
