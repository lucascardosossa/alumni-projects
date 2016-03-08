package br.com.webstore.facade;

import java.util.List;

import br.com.webstore.dao.BugTrackDao;
import br.com.webstore.model.BugTrack;

public class BugTrackFacade implements IBugTrackFacade{
	// @Inject
	private BugTrackDao categoriaDataProvider;

	@Override
	public BugTrack insertBugTrack(BugTrack bugTrack) {
		return categoriaDataProvider.insert(bugTrack);
	}

	@Override
	public void updateBugTrack(BugTrack bugTrack) {
		categoriaDataProvider.update(bugTrack);
	}

	@Override
	public List<BugTrack> findBugTrack(BugTrack query) {
		return categoriaDataProvider.getList();
	}

}
