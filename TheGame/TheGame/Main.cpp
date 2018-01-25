#include <iostream>
#include <new>
#include <SDL.h>
#include <stdio.h>
#include "Game.h"

const int SCREEN_WIDTH = 640;
const int SCREEN_HEIGHT = 480;

using namespace std;

Game game;

int main(int argc, char* args[])
{
	const int FPS = 100;
	const int frameDalay = 1000 / FPS;

	Uint32 frameStart;
	int frameTime;

	game.init("MyGame", 1270, 720, false);

	while (game.running()) {
		frameStart = SDL_GetTicks();

		game.handleEvents();
		game.update();
		game.render();

		frameTime = SDL_GetTicks() - frameStart;

		if (frameDalay > frameTime)
		{
			SDL_Delay(frameDalay - frameTime);
		}
	}
	game.clean();
	return 0;
}