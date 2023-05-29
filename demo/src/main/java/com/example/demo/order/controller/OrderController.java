package com.example.demo.order.controller;

import com.example.demo.account.service.AccountService;
import com.example.demo.order.controller.form.OrderRegisterRequestForm;
import com.example.demo.order.dto.OrderDTO;
import com.example.demo.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor

@RequestMapping("/order")
public class OrderController {
    final private OrderService orderService;
    final private AccountService accountService;

    @GetMapping("/history/{accountId}")
    public List<OrderDTO> orderList(@PathVariable("accountId") Long accountId) {
        log.info("orderList()");
        List<OrderDTO> returnedOrderList = orderService.list(accountId);

        log.info("returnedProductList: " + returnedOrderList.get(0).toString());
        return returnedOrderList;
    }
    @GetMapping("/{orderId}")
    public OrderDTO order (@PathVariable("orderId") Long orderId) {
        log.info("readOrder()");
        OrderDTO order = orderService.read(orderId);
        return order;
    }

    @DeleteMapping("/{orderId}")
    public void deleteOrder (@PathVariable("orderId") Long orderId) {
        log.info("deleteOrder()");

        orderService.delete(orderId);
    }

    @PostMapping("/register")
    public Boolean orderRegister (@RequestBody OrderRegisterRequestForm requestForm) {
        final Long accountId = accountService.findAccountId(requestForm.getAccountToken());

        return orderService.register(accountId, requestForm.toOrderRegisterRequest());
    }
}
