package dev.ener_track.com.demo.infracture.adstract_service.generic;

import dev.ener_track.com.demo.utils.enums.SortType;
import org.springframework.data.domain.Page;

public interface  ReadAllService<Response> {
    Page<Response> getAll(int page, int size, SortType sortType);
}