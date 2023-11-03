package br.com.samueljunnior.core.pagination;


import java.io.Serializable;
import java.util.List;

public final class PageableConverter {
    public static <T extends Serializable> PageableReponser<T> toPageResponse(final Long totalItems, int totalPages, List<T> items) {
        final PageableReponser<T> response = new PageableReponser<>();
        response.setTotalItems(totalItems);
        response.setTotalPages(totalPages);
        response.setItems(items);
        return response;
    }
}
