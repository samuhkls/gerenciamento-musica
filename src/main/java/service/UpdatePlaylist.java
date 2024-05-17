package service;

import model.Playlist;

import java.util.List;
import dao.PlaylistDAO;

public class UpdatePlaylist {
    private PlaylistDAO playlistDAO;

    public UpdatePlaylist(PlaylistDAO playlistDAO) {
        this.playlistDAO = playlistDAO;
    }

    public void updatePlaylistName(int playlistId, String newName) {
        // Buscar a playlist pelo id
        Playlist playlist = playlistDAO.findById(playlistId);

        // Alterar o nome da playlist
        playlist.setNomePLaylist(newName);

        // Salvar a playlist atualizada
        playlistDAO.updatePlaylistName(playlist);
    }
}
