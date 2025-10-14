package dev.ener_track.com.msvc_users.infrastructure.adstract_service.generic;

import dev.ener_track.com.msvc_users.utils.emuns.SortType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface  ReadAllService<Response> {
    Page<Response> getAll(int page, int size, SortType sortType);
}