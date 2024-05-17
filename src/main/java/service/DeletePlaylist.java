package service;

import dao.PlaylistDAO;

public class DeletePlaylist {
    private PlaylistDAO playlistDAO;

    public DeletePlaylist() {
        this.playlistDAO = new PlaylistDAO();
    }

    public boolean deletePlaylist(String playlistId) {
        return playlistDAO.deletePlaylist(playlistId);
    }
}
