#include "Game.h"
#include <SDL.h>
#include "TextureLoader.h"
#include "Map.h"
#include <stdio.h>
#include <iostream>
#include "ECS.h"
#include "Vector2D.h"
#include "Collision.h"
#include "Components.h"



using namespace std;
Map* map;

Vector2D* vector;
SDL_Renderer* Game::renderer = nullptr;
SDL_Event Game::event;

Manager manager;

auto&  wall_1(manager.addEntity());
auto&  wall_2(manager.addEntity());
auto&  wall_3(manager.addEntity());
auto&  wall_4(manager.addEntity());
auto&  wall_5(manager.addEntity());
auto&  speedBonus(manager.addEntity());
auto&  player(manager.addEntity());

auto&  finish(manager.addEntity());

Game::Game()
{

}

Game::~Game()
{

}

void Game::init(const char* title, int width, int height, bool fullscreen) {

	int flags = 0;
	if (fullscreen) {
		flags = SDL_WINDOW_FULLSCREEN;
	}

	//inicjalizacja sdl`u
	if (SDL_Init(SDL_INIT_EVERYTHING) == 0) {
		cout << "SDL Initialized..." << endl;

		window = SDL_CreateWindow(title, SDL_WINDOWPOS_CENTERED, SDL_WINDOWPOS_CENTERED, width, height, flags);
		if (window) {
			cout << "Window created..." << endl;
		}

		renderer = SDL_CreateRenderer(window, -1, 0);
		if (renderer) {
			SDL_SetRenderDrawColor(renderer, 255, 255, 255, 255);
			cout << "render created..." << endl;
		}
		isRunning = true;	
	}

	//Background loading
	map = new Map();
	initilizeEntities();
}

void Game::handleEvents() {

	SDL_PollEvent(&event);
		switch (event.type){
		case SDL_QUIT:
			isRunning = false;
			break;
		default:
			break;
		}
}

void Game::update() {

	manager.refresh();
    manager.update();

	//Granicę ekranów
	if (player.getComponent<MovementComponent>().position.y >= 700 || player.getComponent<MovementComponent>().position.y <= 0) {
		player.getComponent<MovementComponent>().velocity.y = player.getComponent<MovementComponent>().velocity.y * -1;
	}

	if (player.getComponent<MovementComponent>().position.x >= 1200 || player.getComponent<MovementComponent>().position.x <= 0) {
		player.getComponent<MovementComponent>().velocity.x = player.getComponent<MovementComponent>().velocity.x * -1;
	}

	//Sprawdzamy kolizje
	checkCollisions();

	//Jeżeli strenght(health) poniżej 0 - zaczynamy od startu
	if (player.getComponent<MovementComponent>().strenght <= 0) {
		player.getComponent<MovementComponent>().position.x = 100.0f;
		player.getComponent<MovementComponent>().position.y = 100.0f;
		player.getComponent<MovementComponent>().strenght = 100.0;
	}

	//Jeżeli czas się skonczył
	if (player.getComponent<World>().time <= 0) {
		clean();
	}

	//Konczymy grę
	if (Collision::isVerticalCollision(player.getComponent<ColiderComponent>().collider, finish.getComponent<ColiderComponent>().collider)) {
		clean();
	}
	
}
void Game::initilizeEntities() {
	speedBonus.addComponent<World>();
	speedBonus.addComponent<MovementComponent>(100.0f, 700.0f, 16, 16, 1, true);
	speedBonus.addComponent<SpriteComponent>("Res/rocks.png");
	speedBonus.addComponent<ColiderComponent>("bonus");

	wall_1.addComponent<World>();
	wall_1.addComponent<MovementComponent>(500.0f, 20.0f, 300, 16, 1, true);
	wall_1.addComponent<SpriteComponent>("Res/ground.png");
	wall_1.addComponent<ColiderComponent>("wall");

	wall_2.addComponent<World>();
	wall_2.addComponent<MovementComponent>(300.0f, 300.0f, 300, 25, 1, true);
	wall_2.addComponent<SpriteComponent>("Res/rocks.png");
	wall_2.addComponent<ColiderComponent>("wall");

	wall_3.addComponent<World>();
	wall_3.addComponent<MovementComponent>(420.0f, 420.0f, 10, 500, 1, true);
	wall_3.addComponent<SpriteComponent>("Res/rocks.png");
	wall_3.addComponent<ColiderComponent>("wall");

	wall_4.addComponent<World>();
	wall_4.addComponent<MovementComponent>(800.0f, 280.0f, 300, 20, 1, true);
	wall_4.addComponent<SpriteComponent>("Res/rocks.png");
	wall_4.addComponent<ColiderComponent>("wall");

	wall_5.addComponent<World>();
	wall_5.addComponent<MovementComponent>(1100.0f, 350.0f, 400, 20, 1, true);
	wall_5.addComponent<SpriteComponent>("Res/rocks.png");
	wall_5.addComponent<ColiderComponent>("wall");

	player.addComponent<World>();
	player.addComponent<MovementComponent>(100.0f, 100.0f, 64, 64, 100);
	player.addComponent<SpriteComponent>("Res/Player.png");
	player.addComponent<InputHandler>();
	player.addComponent<ColiderComponent>("player");

	finish.addComponent<World>();
	finish.addComponent<MovementComponent>(1150.0f, 650.0f, 32, 71, 1, true);
	finish.addComponent<SpriteComponent>("Res/finish.png");
	finish.addComponent<InputHandler>();
	finish.addComponent<ColiderComponent>("finish");
}

//Ustawienie kolizji z objektami
void Game::checkCollisions() {
	if (Collision::isVerticalCollision(player.getComponent<ColiderComponent>().collider, wall_1.getComponent<ColiderComponent>().collider)) {
		std::cout << "Vertical Collision " << std::endl;
		player.getComponent<MovementComponent>().velocity.y = player.getComponent<MovementComponent>().velocity.y * -1;
		player.getComponent<MovementComponent>().changeStrength(-20.0);
		std::cout << "Health: " << player.getComponent<MovementComponent>().strenght << std::endl;
	}

	if (Collision::isHorizontalCollision(player.getComponent<ColiderComponent>().collider, wall_1.getComponent<ColiderComponent>().collider)) {
		std::cout << "Horizontal Collision " << std::endl;
		player.getComponent<MovementComponent>().velocity.x = player.getComponent<MovementComponent>().velocity.x * -1;
		player.getComponent<MovementComponent>().changeStrength(-20.0);
		std::cout << "Health: " << player.getComponent<MovementComponent>().strenght << std::endl;
	}

	if (Collision::isVerticalCollision(player.getComponent<ColiderComponent>().collider, wall_2.getComponent<ColiderComponent>().collider)) {
		std::cout << "Vertical Collision " << std::endl;
		player.getComponent<MovementComponent>().velocity.y = player.getComponent<MovementComponent>().velocity.y * -1;
		player.getComponent<MovementComponent>().changeStrength(-20.0);
		std::cout << "Health: " << player.getComponent<MovementComponent>().strenght << std::endl;
	}

	if (Collision::isHorizontalCollision(player.getComponent<ColiderComponent>().collider, wall_2.getComponent<ColiderComponent>().collider)) {
		std::cout << "Horizontal Collision " << std::endl;
		player.getComponent<MovementComponent>().velocity.x = player.getComponent<MovementComponent>().velocity.x * -1;
		player.getComponent<MovementComponent>().changeStrength(-20.0);
		std::cout << "Health: " << player.getComponent<MovementComponent>().strenght << std::endl;
	}

	if (Collision::isVerticalCollision(player.getComponent<ColiderComponent>().collider, wall_3.getComponent<ColiderComponent>().collider)) {
		std::cout << "Vertical Collision " << std::endl;
		player.getComponent<MovementComponent>().velocity.y = player.getComponent<MovementComponent>().velocity.y * -1;
		player.getComponent<MovementComponent>().changeStrength(-20.0);
		std::cout << "Health: " << player.getComponent<MovementComponent>().strenght << std::endl;
	}

	if (Collision::isHorizontalCollision(player.getComponent<ColiderComponent>().collider, wall_3.getComponent<ColiderComponent>().collider)) {
		std::cout << "Horizontal Collision " << std::endl;
		player.getComponent<MovementComponent>().velocity.x = player.getComponent<MovementComponent>().velocity.x * -1;
		player.getComponent<MovementComponent>().changeStrength(-20.0);
		std::cout << "Health: " << player.getComponent<MovementComponent>().strenght << std::endl;
	}

	if (Collision::isVerticalCollision(player.getComponent<ColiderComponent>().collider, wall_4.getComponent<ColiderComponent>().collider)) {
		std::cout << "Vertical Collision " << std::endl;
		player.getComponent<MovementComponent>().velocity.y = player.getComponent<MovementComponent>().velocity.y * -1;
		player.getComponent<MovementComponent>().changeStrength(-20.0);
		std::cout << "Health: " << player.getComponent<MovementComponent>().strenght << std::endl;
	}

	if (Collision::isHorizontalCollision(player.getComponent<ColiderComponent>().collider, wall_4.getComponent<ColiderComponent>().collider)) {
		std::cout << "Horizontal Collision " << std::endl;
		player.getComponent<MovementComponent>().velocity.x = player.getComponent<MovementComponent>().velocity.x * -1;
		player.getComponent<MovementComponent>().changeStrength(-20.0);
		std::cout << "Health: " << player.getComponent<MovementComponent>().strenght << std::endl;
	}

	if (Collision::isVerticalCollision(player.getComponent<ColiderComponent>().collider, wall_5.getComponent<ColiderComponent>().collider)) {
		std::cout << "Vertical Collision " << std::endl;
		player.getComponent<MovementComponent>().velocity.y = player.getComponent<MovementComponent>().velocity.y * -1;
		player.getComponent<MovementComponent>().changeStrength(-20.0);
		std::cout << "Health: " << player.getComponent<MovementComponent>().strenght << std::endl;
	}

	if (Collision::isHorizontalCollision(player.getComponent<ColiderComponent>().collider, wall_5.getComponent<ColiderComponent>().collider)) {
		std::cout << "Horizontal Collision " << std::endl;
		player.getComponent<MovementComponent>().velocity.x = player.getComponent<MovementComponent>().velocity.x * -1;
		player.getComponent<MovementComponent>().changeStrength(-20.0);
		std::cout << "Health: " << player.getComponent<MovementComponent>().strenght << std::endl;
	}
}

void Game::render() {
	SDL_RenderClear(renderer);
	map->DrawMap();
	manager.draw();
	SDL_RenderPresent(renderer);
}

void Game::clean() {
	SDL_DestroyWindow(window);
	SDL_DestroyRenderer(renderer);
	SDL_Quit(); 
	cout << "Game destroyed..." << endl;
}