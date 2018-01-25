#pragma once
#include <SDL.h>
#include <SDL_image.h>
#include <iostream>
#include <stdio.h>

class Game
{
public:
	Game();
	~Game();

	void init(const char* title, int width, int height, bool fullscreen);

	void update();
	void render();
	void clean();

	void handleEvents();
	void initilizeEntities();
	void checkCollisions();
	bool running() { return isRunning; }

	static SDL_Renderer* renderer;
	static SDL_Event event;

private: 
	bool isRunning;
	SDL_Window *window;

};

