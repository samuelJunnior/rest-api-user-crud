package br.com.samueljunnior.client.service;

import br.com.samueljunnior.client.ViaCepClient;
import br.com.samueljunnior.client.dto.ViaCepReponserDTO;
import br.com.samueljunnior.core.message.MessagePropertiesEnum;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ViaCepService {

    private final ViaCepClient viaCepClient;

    public ViaCepReponserDTO findCep(String cep){
       try {
           final var responser = viaCepClient.getJson(cep);
           final var body = responser.getBody();
           if(Objects.isNull(body)){
               throw MessagePropertiesEnum.API_NOT_FOUND.resourceNotFoundException();
           }
           if( body.isErro()){
               throw MessagePropertiesEnum.VIA_CEP_INTEGRATIONS_ERROR.businessException();
           }

           return body;
       }catch (FeignException.FeignClientException fe) {
           throw MessagePropertiesEnum.VIA_CEP_INTEGRATIONS_ERROR.businessException();
       }catch (ResponseStatusException rs){
           throw rs;
       } catch (Exception e){
           throw MessagePropertiesEnum.API_UNIDENTIFIED_ERROR.businessException(e);
       }
    }

}
