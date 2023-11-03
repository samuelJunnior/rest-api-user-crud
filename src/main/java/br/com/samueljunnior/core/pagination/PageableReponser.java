package br.com.samueljunnior.core.pagination;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class PageableReponser <T extends Serializable> implements Serializable {
    @Serial
    private static final long serialVersionUID = 547160067615890102L;

    private Long totalItems;
    private int totalPages;
    private List<T> items;
}
