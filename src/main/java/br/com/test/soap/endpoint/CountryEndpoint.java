package br.com.test.soap.endpoint;

import br.com.test.soap.model.GetCountryRequest;
import br.com.test.soap.model.GetCountryResponse;
import br.com.test.soap.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint //Candidado a processar as mensagens soap que chega
public class CountryEndpoint  {

  private CountryRepository countryRepository;

  private static final String NAMESPACE_URI = "http://soap.test.com.br/model";

  @Autowired
  public CountryEndpoint(CountryRepository countryRepository) {
    this.countryRepository = countryRepository;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
  @ResponsePayload
  public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
    GetCountryResponse response = new GetCountryResponse();
    response.setCountry(countryRepository.findCountry(request.getName()));

    return response;
  }


}
