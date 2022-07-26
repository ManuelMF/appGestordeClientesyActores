package Controlador;

import Modelo.Playlist;

public class MenuMiPlaylist {
	Vista.menuMiPlaylist VentanaPalylist;
	
	public MenuMiPlaylist(Playlist playlsit) {
		VentanaPalylist = new Vista.menuMiPlaylist();
	}
}