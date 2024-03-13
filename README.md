## Welcome to the NowService Project
<p>
  This project provides an all out back-end approach to building connectors between SAP and any third party vendor which requires to integrate SAP modules.
</p>

## Getting started
1. Clone the repo:
   ```sh
   git clone https://github.com/nirvishek/NowService.git
   ```
2. Create **application.properties** file in your resource directory:
   ```src/main/resources```
3. Paste these sample properties and update values as per required.
   ```
   username=<sap_instance_username>
   password=<sap_instance_password>

   LegalEntity=https://<SAP_INSTANCE_URL>/sap/opu/odata/sap/API_COMPANYCODE_SRV/A_CompanyCode?$top=${top}&$inlinecount=allpages&$format=json&$filter=${filter}&$orderby=${orderBy}&$select=${select}
    
   top=
   filter=
   select=
   orderBy=
   
   ```
  4. Run project.
