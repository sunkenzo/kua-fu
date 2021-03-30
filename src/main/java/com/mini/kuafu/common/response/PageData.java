package com.mini.kuafu.common.response;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author kenzo
 * @create 2021-02-25 10:16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageData<T> {
    /**
     * 数据列表
     */
    @NonNull
    private List<T> records;
    /**
     * 总记录数
     */
    @NonNull
    private Long totalCount = 0L;

    /**
     * 总页数
     */
    @NonNull
    private Long pages = 0L;

    /**
     * 当前页，从1开始
     */
    @NonNull
    private Long page = 1L;

    /**
     * 每页显示条数
     */
    @NonNull
    private Long size = 10L;

    public PageData(IPage<T> page) {
        this.setRecords(page.getRecords());
        this.setTotalCount(page.getTotal());
        this.setPages(page.getPages());
        this.setPage(page.getCurrent());
        this.setSize(page.getSize());
    }

    public <E> PageData(IPage<E> page, Function<E, T> mapper) {
        this.setTotalCount(page.getTotal());
        this.setPages(page.getPages());
        this.setPage(page.getCurrent());
        this.setSize(page.getSize());

        if (CollectionUtils.isEmpty(page.getRecords())) {
            this.setRecords(Collections.emptyList());
        } else {
            this.setRecords(page.getRecords().stream().map(mapper).collect(Collectors.toList()));
        }
    }

    public PageData(List collect, long count, long page, long size) {
        this.setRecords(collect);
        this.setTotalCount(count);
        this.setPage(page);
        this.setSize(size);
        this.setPages(pages());
    }

    private Long pages() {
        if (totalCount == 0) {
            return 1L;
        }

        long pages = getTotalCount() / getSize();
        if (getTotalCount() % getSize() != 0){
            pages++;
        }

        return pages;
    }
}
