package reco.core.rule.service;

import org.springframework.stereotype.Service;
import reco.core.rule.dao.IdsRepository;
import javax.annotation.Resource;

@Service
public class IdServiceImpl implements IdService{
    @Resource
    private IdsRepository idsRepository;
    @Override
    public int getNext(String name) {
        return idsRepository.getNext(name);
    }
}
