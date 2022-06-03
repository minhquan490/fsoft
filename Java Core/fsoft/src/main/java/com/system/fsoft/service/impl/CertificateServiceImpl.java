package com.system.fsoft.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.system.fsoft.entity.Candidate;
import com.system.fsoft.entity.Certificate;
import com.system.fsoft.exception.CertificateNotFoundException;
import com.system.fsoft.repository.CertificateRepository;
import com.system.fsoft.repository.impl.CertificateRepositoryImpl;
import com.system.fsoft.service.CertificateService;

public class CertificateServiceImpl implements CertificateService {

	private CertificateRepository repository = new CertificateRepositoryImpl();

	@Override
	public void saveOrUpdate(Certificate certificate) throws SQLException {
		if (certificate.getCertificatedID() == null) {
			repository.saveOrUpdate(certificate);
		} else {
			Certificate oldCertificate = this.get(certificate.getCertificatedID());
			if (oldCertificate == null) {
				throw new CertificateNotFoundException(
						"Certificate is not exist or deleted, please reload your list to continue");
			}
			oldCertificate.setCertificatedName(certificate.getCertificatedName());
			oldCertificate.setCertificatedRank(certificate.getCertificatedRank());
			oldCertificate.setCertificatedDate(certificate.getCertificatedDate());
			repository.saveOrUpdate(certificate);
		}
	}

	@Override
	public void delete(Certificate certificate) throws SQLException {
		repository.delete(certificate);
	}

	@Override
	public Certificate get(String certificateID) throws SQLException {
		return repository.get(certificateID);
	}

	@Override
	public List<Certificate> getCertificatesByCandidate(Candidate candidate) throws SQLException {
		return repository.getCertificatesByCandidate(candidate);
	}

}