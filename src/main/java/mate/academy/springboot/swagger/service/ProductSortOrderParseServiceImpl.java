package mate.academy.springboot.swagger.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductSortOrderParseServiceImpl implements ProductSortOrderParseService {
    private static final Sort.Direction DEFAULT = Sort.Direction.ASC;

    @Override
    public List<Sort.Order> parse(String sortBy) {
        List<Sort.Order> orders = new ArrayList<>();
        if (sortBy.contains(":")) {
            String[] sortFields = sortBy.split(";");
            for (String sortField : sortFields) {
                if (sortField.contains(":")) {
                    String[] fieldAndDirection = sortField.split(":");
                    orders.add(new Sort.Order(Sort.Direction.fromString(fieldAndDirection[1]),
                            fieldAndDirection[0]));
                } else {
                    orders.add(new Sort.Order(DEFAULT, sortField));
                }
            }
        } else {
            orders.add(new Sort.Order(DEFAULT, sortBy));
        }
        return orders;
    }
}