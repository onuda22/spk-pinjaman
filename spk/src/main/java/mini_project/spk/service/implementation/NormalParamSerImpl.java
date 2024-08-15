package mini_project.spk.service.implementation;

import lombok.RequiredArgsConstructor;
import mini_project.spk.model.NormalizationParameter;
import mini_project.spk.repository.NormalizationRepo;
import mini_project.spk.service.NormalizationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NormalParamSerImpl implements NormalizationService {
    private final NormalizationRepo repo;

    @Override
    public List<NormalizationParameter> getAll() {
        return repo.findNormalizationParameterAll();
    }

    @Override
    public NormalizationParameter create(NormalizationParameter request) {
        return repo.saveNormalizationParameter(
                request.getParamName(),
                request.getMinValue(),
                request.getMaxValue()
        );
    }

    @Override
    public NormalizationParameter updateById(Integer id, NormalizationParameter updateReq) {
        return repo.updateNormalizationParameter(
                id,
                updateReq.getParamName(),
                updateReq.getMinValue(),
                updateReq.getMaxValue()
        );
    }

    @Override
    public void deleteById(Integer id) {
        repo.deleteById(id);
    }
}
