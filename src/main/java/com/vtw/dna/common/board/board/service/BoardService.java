package com.vtw.dna.common.board.board.service;

import com.vtw.dna.common.board.board.dto.BoardCommand;
import com.vtw.dna.common.board.board.dto.BoardFile;
import com.vtw.dna.common.board.board.dto.BoardFilter;
import com.vtw.dna.common.board.board.dto.BoardQuery;
import com.vtw.dna.common.board.board.repository.BoardRepository;
import com.vtw.dna.common.exception.NoSuchEntityException;
import com.vtw.dna.common.rest.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository repository;
    private String uploadDir = "C:/dna-admin/upload/";

    public Page<BoardQuery> list(BoardFilter filter, Pageable pageable) throws Exception {
        int count = repository.count(filter, pageable);
        List<BoardQuery> list = repository.findAll(filter, pageable);
        List<BoardQuery> pinList = repository.findPinAll(filter.getBoardMasterId());
        list.removeAll(pinList);
        list.forEach(e -> e.setPinYn(false));
        List<BoardQuery> joined = Stream.concat(pinList.stream(), list.stream())
          .collect(Collectors.toList());
        Page<BoardQuery> page = Page.<BoardQuery>builder().totalCount(count).data(joined).build();
        return page;
    }

    public List<BoardQuery> popupList() throws Exception {
        List<BoardQuery> popups = repository.findPopupAll();
        return popups;
    }

    public void removeFile(Long id) throws Exception {
        repository.removeFile(id);
    }

    public BoardQuery find(Long id) throws Exception {
        BoardQuery entity = repository.findById(id).orElseThrow(() -> new NoSuchEntityException("Board", id));
        repository.addViewCount(id);
        entity.setFiles(selectFileList(id));
        return entity;
    }

    public List<BoardFile> selectFileList(Long id) throws Exception {
        List<BoardFile> list = repository.selectFileList(id);
        return list;
    }

    public ResponseEntity download(Long id, HttpServletResponse response) throws Exception {
        BoardFile fileInfo = repository.selectFile(id);

        byte fileByte[] = org.apache.commons.io.FileUtils.readFileToByteArray(new File(String.valueOf(Paths.get(fileInfo.getFilePath()))));

//        response.setContentType("application/octet-stream");
//        response.setContentLength(fileByte.length);
//        response.setHeader("Content-Disposition",  "attachment; fileName=\""+ URLEncoder.encode(file.getOriginalFileName(), "UTF-8")+"\";");
//        response.getOutputStream().write(fileByte);
//        response.getOutputStream().flush();
//        response.getOutputStream().close();

        Path filePath = Paths.get(fileInfo.getFilePath());

        String fileName = fileInfo.getOriginalFileName();

        Resource resource = new InputStreamResource(Files.newInputStream(filePath)); // 파일 resource 얻기

        File file = new File(String.valueOf(Paths.get(fileInfo.getFilePath())));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(ContentDisposition.builder("attachment").filename(file.getName()).build()); // 다운로드
        return new ResponseEntity<Object>(resource, headers, HttpStatus.OK);
    }

    public void create(BoardCommand entity) throws Exception {
        entity.setBoardNo(calcBoardNo(entity));
        entity.setViewCount(0L);
        repository.insert(entity);
    }

    public void upload(Long boardId, List<MultipartFile> files) throws Exception {
        File cFile = new File(uploadDir);

        if (!cFile.isDirectory()) {
            boolean _flag = cFile.mkdirs();
            if (!_flag) {
                throw new IOException("Directory creation Failed ");
            }
        }

        for (MultipartFile file : files) {
            String storeFileName = file.getOriginalFilename() + getTimeStamp();
            Path copyOfLocation = Paths.get(uploadDir + File.separator + StringUtils.cleanPath(storeFileName));
            try {
                Files.copy(file.getInputStream(), copyOfLocation, StandardCopyOption.REPLACE_EXISTING);
                BoardFile boardFile = new BoardFile();
                boardFile.setBoardId(boardId);
                boardFile.setFilePath(copyOfLocation.toString());
                boardFile.setOriginalFileName(file.getOriginalFilename());
                boardFile.setStoreFileName(storeFileName);
                boardFile.setUseYn(true);

                repository.upload(boardFile);
            } catch (IOException e) {
                e.printStackTrace();
                throw new Exception();
            }
        }
    }

    private String getTimeStamp() {

        String rtnStr = null;

        // 문자열로 변환하기 위한 패턴 설정(연도-월-일 시:분:초:초(자정이후 초))
        String pattern = "yyyyMMddhhmmssSSS";

        SimpleDateFormat sdfCurrent = new SimpleDateFormat(pattern, Locale.KOREA);
        Timestamp ts = new Timestamp(System.currentTimeMillis());

        rtnStr = sdfCurrent.format(ts.getTime());

        return rtnStr;
    }


    private Long calcBoardNo(BoardCommand entity) {
        Long boardNo = repository.countByBoardType(entity.getBoardMasterId()) + 1;
        return boardNo;
    }

    public void update(BoardCommand entity) throws Exception {
        find(entity.getId()); // 해당 ID의 Entity가 존재하지 않으면 Exception 발생
        repository.update(entity);
    }

    public void delete(BoardCommand entity) throws Exception {
        repository.delete(entity);
    }

}
