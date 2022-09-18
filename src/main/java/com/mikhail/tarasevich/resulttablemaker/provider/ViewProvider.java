package com.mikhail.tarasevich.resulttablemaker.provider;

import java.util.Set;

import com.mikhail.tarasevich.resulttablemaker.domain.Racer;

public interface ViewProvider {
    StringBuilder provideResultTableView (Set<Racer> racerList);
}
