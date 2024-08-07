package com.pwdk.minpro_be.vouchers.service;

import com.pwdk.minpro_be.users.entity.User;
import com.pwdk.minpro_be.vouchers.dto.CreateVoucherRequestDto;
import com.pwdk.minpro_be.vouchers.entity.EventVoucher;
import com.pwdk.minpro_be.vouchers.entity.UserVoucher;
import com.pwdk.minpro_be.vouchers.entity.Voucher;

import java.util.List;
import java.util.Optional;

public interface VoucherService {
    List<UserVoucher> getUserVouchers(String email);
    List<EventVoucher> getEventVouchers(String eventSlug);
    EventVoucher createEventVoucher(CreateVoucherRequestDto createVoucherRequestDto,
                                    String eventSlug,
                                    String userEmail);
    void addUserVoucher(User user, Voucher voucher);
    Voucher getVoucherById(Long id);

    Voucher getVoucherByName(String voucherName);

    Optional<EventVoucher> getByEventIdAndVoucherId(Long eventId, Long voucherId);
    Optional<UserVoucher> getByUserIdAndVoucherId(Long userId, Long voucherId);
}
