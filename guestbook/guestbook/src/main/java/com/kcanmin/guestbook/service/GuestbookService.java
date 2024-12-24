package com.kcanmin.guestbook.service;

import java.util.List;

import com.kcanmin.guestbook.domain.dto.GuestbookListDTO;
import com.kcanmin.guestbook.domain.dto.GuestbookModifyDTO;
import com.kcanmin.guestbook.domain.dto.GuestbookViewDTO;
import com.kcanmin.guestbook.domain.dto.GuestbookWriteDTO;

public interface GuestbookService {
  void write(GuestbookWriteDTO dto);
  void modify(GuestbookModifyDTO dto);
  // void remove(GuestbookRemoveDTO dto);
  List<GuestbookListDTO> list();
  GuestbookViewDTO get(Long gno);
}
