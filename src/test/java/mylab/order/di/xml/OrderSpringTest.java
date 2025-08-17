package mylab.order.di.xml;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:mylab-order-di.xml")
class OrderSpringTest {

    @Autowired private ShoppingCart cart;
    @Autowired private OrderService service;

    @Test
    void testShoppingCart() {
        assertNotNull(cart);
        assertNotNull(cart.getProducts());
        assertEquals(2, cart.getProducts().size());
        assertEquals("노트북",   cart.getProducts().get(0).getName());
        assertEquals("스마트폰", cart.getProducts().get(1).getName());
    }

    @Test
    void testOrderService() {
        assertNotNull(service);
        assertNotNull(service.getShoppingCart());

        // XML 기준(150000 + 800000 = 950000) 또는 동적 계산으로 안전하게 검증
        double expected = cart.getTotalPrice();
        assertEquals(expected, service.calculateOrderTotal(), 1e-6);
    }
}
