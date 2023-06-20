
## SupplierController

Este controlador lida com as operações relacionadas aos fornecedores.

### Obter fornecedor por tipo de documento e CNPJ

Obtém um fornecedor com base no tipo de documento e CNPJ fornecidos.

**URL**
```
GET /supplier/{documentType}/{document}
```

**Parâmetros de URL**
- `documentType` (enum): O tipo de documento do fornecedor (possíveis valores: "CPF", "CNPJ").
- `document` (string): O número do documento do fornecedor baseado no tipo.

**Códigos de Resposta**
- 200 OK: Retorna o fornecedor encontrado.
- 404 Not Found: Se o fornecedor não for encontrado.

**Exemplo de Requisição**
```
GET /supplier/CPF/12345678900
```

**Exemplo de Resposta**
```json
{
  "document": "12345678900",
  "documentType": "CPF",
  "name": "John Doe",
  "email": "johndoe@example.com",
  "address": {
    "street": "Rua A",
    "number": 123,
    "complement": "",
    "neighborhood": "Centro",
    "city": "São Paulo",
    "state": "SP",
    "zipCode": "12345-678",
    "country": "Brasil"
  },
  "birthDate": "1990-01-01",
  "rg": "123456789"
}
```

### Obter fornecedor por ID

Obtém um fornecedor com base no ID fornecido.

**URL**
```
GET /supplier/{id}
```

**Parâmetros de URL**
- `id` (long): O ID do fornecedor.

**Códigos de Resposta**
- 200 OK: Retorna o fornecedor encontrado.
- 404 Not Found: Se o fornecedor não for encontrado.

**Exemplo de Requisição**
```
GET /supplier/1
```

**Exemplo de Resposta**
```json
{
  "document": "12345678900",
  "documentType": "CPF",
  "name": "John Doe",
  "email": "johndoe@example.com",
  "address": {
    "street": "Rua A",
    "number": 123,
    "complement": "",
    "neighborhood": "Centro",
    "city": "São Paulo",
    "state": "SP",
    "zipCode": "12345-678",
    "country": "Brasil"
  },
  "birthDate": "1990-01-01",
  "rg": "123456789"
}
```

### Obter todos os fornecedores

Obtém todos os fornecedores cadastrados.

**URL**
```
GET /supplier
```

**Códigos de Resposta**
- 200 OK: Retorna a lista de todos os fornecedores.

**Exemplo de Requisição**
```
GET /supplier
```

**Exemplo de Resposta**
```json
[
  {
    "document": "12345678900",
    "documentType": "CPF",
    "name": "John Doe",
    "email": "johndoe@example.com",
    "address": {
      "street": "Rua A",
      "number": 123,
      "complement": "",
      "neighborhood": "Centro",
      "city": "São Paulo",
      "state": "SP",
      "zipCode": "12345-678",
      "country": "Brasil"
    },
    "birthDate": "1990-01-01",
    "rg": "123456789"
  },
  {
    "document": "98765432100",
    "documentType": "CPF",
    "name": "Jane Smith",
    "email": "janesmith@example.com",
    "address": {
      "street": "Rua B",
      "number": 456,
      "complement": "",
      "neighborhood": "Centro",
      "city": "São Paulo",
      "state": "SP",
      "zipCode": "12345-678",
      "country": "Brasil"
    },
    "birthDate": "1995-02-15",
    "rg": "987654321"
  }
]
```

### Adicionar fornecedor

Adiciona um novo fornecedor.

**URL**
```
POST /supplier
```

**Parâmetros de Solicitação**
- `AddSupplierRequest` (objeto): Os dados do fornecedor a ser adicionado.

**Códigos de Resposta**
- 201 Created: O fornecedor foi adicionado com sucesso.

**Exemplo de Requisição**
```
POST /supplier
Content-Type: application/json

{
  "document": "12345678900",
  "documentType": "CPF",
  "name": "John Doe",
  "email": "johndoe@example.com",
  "address": {
    "street": "Rua A",
    "number": 123,
    "complement": "",
    "neighborhood": "Centro",
    "city": "São Paulo",
    "state": "SP",
    "zipCode": "12345-678",
    "country": "Brasil"
  },
  "birthDate": "1990-01-01",
  "rg": "123456789"
}
```

### Atualizar fornecedor

Atualiza os dados de um fornecedor existente.

**URL**
```
PUT /supplier/{id}
```

**Parâmetros de URL**
- `id` (long): O ID do fornecedor a ser atualizado.

**Parâmetros de Solicitação**
- `UpdateSupplierRequest` (objeto): Os novos dados do fornecedor.

**Códigos de Resposta**
- 200 OK: O fornecedor foi atualizado com sucesso.
- 404 Not Found: Se o fornecedor não for encontrado.

**Exemplo de Requisição**
```
PUT /supplier/1
Content-Type: application/json

{
  "document": "98765432100",
  "documentType": "CPF",
  "name": "Jane Smith",
  "email": "janesmith@example.com",
  "address": {
    "street": "Rua B",
    "number": 456,
    "complement": "",
    "neighborhood": "Centro",
    "city": "São Paulo",
    "state": "SP",
    "zipCode": "12345-678",
    "country": "Brasil"
  },
  "birthDate": "1995-02-15",
  "rg": "987654321"
}
```

### Remover fornecedor

Remove um fornecedor existente.

**URL**
```
DELETE /supplier/{id}
```

**Parâmetros de URL**
- `id` (long): O ID do fornecedor a ser removido.

**Códigos de Resposta**
- 204 No Content: O forn

ecedor foi removido com sucesso.
- 404 Not Found: Se o fornecedor não for encontrado.

**Exemplo de Requisição**
```
DELETE /supplier/1
```

### Adicionar fornecedor a uma empresa

Adiciona um fornecedor a uma empresa existente.

**URL**
```
POST /company/{companyId}/supplier
```

**Parâmetros de URL**
- `companyId` (long): O ID da empresa.

**Parâmetros de Solicitação**
- `AddSupplierToCompany` (objeto): Os dados do fornecedor a ser adicionado.

**Códigos de Resposta**
- 201 Created: O fornecedor foi adicionado à empresa com sucesso.
- 404 Not Found: Se a empresa ou o fornecedor não forem encontrados.

**Exemplo de Requisição**
```
POST /company/1/supplier
Content-Type: application/json

{
  "supplierId": 1
}
```

## DTOs


Claro! Continuando a documentação com base nos DTOs fornecidos:

### UpdateSupplierRequest

DTO utilizado para atualizar os dados de um fornecedor.

**Atributos**
- `document` (string): O CPF ou CNPJ do fornecedor.
- `documentType` (enum): O tipo de documento (CPF ou CNPJ).
- `name` (string): O nome do fornecedor.
- `email` (string): O endereço de e-mail do fornecedor.
- `address` (objeto): Os dados do endereço do fornecedor.
- `birthDate` (data): A data de nascimento do fornecedor (obrigatório apenas para CPF).
- `rg` (string): O RG do fornecedor (obrigatório apenas para CPF).

### UpdateCompanyRequest

DTO utilizado para atualizar os dados de uma empresa.

**Atributos**
- `cnpj` (string): O CNPJ da empresa.
- `tradeName` (string): O nome comercial da empresa.
- `address` (objeto): Os dados do endereço da empresa.

### AddSupplierToCompany

DTO utilizado para adicionar um fornecedor a uma empresa.

**Atributos**
- `supplierId` (long): O ID do fornecedor a ser adicionado.

### AddSupplierRequest

DTO utilizado para adicionar um novo fornecedor.

**Atributos**
- `document` (string): O CPF ou CNPJ do fornecedor.
- `documentType` (enum): O tipo de documento (CPF ou CNPJ).
- `name` (string): O nome do fornecedor.
- `email` (string): O endereço de e-mail do fornecedor.
- `address` (objeto): Os dados do endereço do fornecedor.
- `birthDate` (data): A data de nascimento do fornecedor (obrigatório apenas para CPF).
- `rg` (string): O RG do fornecedor (obrigatório apenas para CPF).

### AddressRequest

DTO utilizado para representar os dados de um endereço.

**Atributos**
- `street` (string): O nome da rua.
- `number` (integer): O número do endereço.
- `complement` (string): O complemento do endereço.
- `neighborhood` (string): O bairro.
- `city` (string): A cidade.
- `state` (string): O estado.
- `zipCode` (string): O CEP.
- `country` (string): O país.

### AddCompanyRequest

DTO utilizado para adicionar uma nova empresa.

**Atributos**
- `cnpj` (string): O CNPJ da empresa.
- `tradeName` (string): O nome comercial da empresa.
- `address` (objeto): Os dados do endereço da empresa.

Esses são os DTOs utilizados na API para representar os dados de entrada e saída nas diversas operações relacionadas a empresas e fornecedores.