package trafficsystem.service;

import trafficsystem.entity.AdminEntity;

public interface AdminService {

    AdminEntity findAdminByIDCard(String idCard);

    void insertAdmin(AdminEntity adminEntity);
}
