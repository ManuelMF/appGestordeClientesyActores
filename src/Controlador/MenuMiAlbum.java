package Controlador;

import Modelo.Album;

public class MenuMiAlbum {
	Vista.menuMiAlbum VentanaAlbum;
	
	public MenuMiAlbum(Album album) {
		VentanaAlbum = new Vista.menuMiAlbum();
	}
}
