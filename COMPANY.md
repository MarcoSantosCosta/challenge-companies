# Company Controller API Documentation

## Base URL

`/api/company`

**Note:** The API does not require authentication.

## Create Company

**Endpoint:** `POST /`

Creates a new company.

### Request Body

```json
{
  "cnpj": "12345678000100",
  "tradeName": "Example Company",
  "address": {
    "zipCode": "12345-678",
    "street": "Example Street",
    "number": "123",
    "city": "Example City",
    "state": "EX",
    "country": "Example Country"
  }
}
```

### Response

- HTTP Status Code: 201 (Created)
- Body:

```json
{
  "id": 1,
  "cnpj": "12345678000100",
  "tradeName": "Example Company",
  "address": {
    "zipCode": "12345-678",
    "street": "Example Street",
    "number": "123",
    "city": "Example City",
    "state": "EX",
    "country": "Example Country"
  }
}
```

## Update Company

**Endpoint:** `PUT /{id}`

Updates an existing company with the specified ID.

### Request Body

```json
{
  "tradeName": "Updated Company",
  "address": {
    "zipCode": "54321-098",
    "street": "Updated Street",
    "number": "456",
    "city": "Updated City",
    "state": "UP",
    "country": "Updated Country"
  }
}
```

### Response

- HTTP Status Code: 200 (OK)
- Body:

```json
{
  "id": 1,
  "cnpj": "12345678000100",
  "tradeName": "Updated Company",
  "address": {
    "zipCode": "54321-098",
    "street": "Updated Street",
    "number": "456",
    "city": "Updated City",
    "state": "UP",
    "country": "Updated Country"
  }
}
```

## Get Company by ID

**Endpoint:** `GET /{id}`

Retrieves a company with the specified ID.

### Response

- HTTP Status Code: 200 (OK)
- Body:

```json
{
  "id": 1,
  "cnpj": "12345678000100",
  "tradeName": "Example Company",
  "address": {
    "zipCode": "12345-678",
    "street": "Example Street",
    "number": "123",
    "city": "Example City",
    "state": "EX",
    "country": "Example Country"
  }
}
```

## Get Company by CNPJ

**Endpoint:** `GET /cnpj/{cnpj}`

Retrieves a company with the specified CNPJ.

### Response

- HTTP Status Code: 200 (OK)
- Body:

```json
{
  "id": 1,
  "cnpj": "12345678000100",
  "tradeName": "Example Company",
  "address": {
    "zipCode": "12345-678",
    "street": "Example Street",
    "number": "123",
    "city": "Example City",
    "state": "EX",
    "country": "Example Country"
  }
}
```

## Get Suppliers for a Company

**Endpoint:** `GET /{id}/suppliers`

Retrieves a list of suppliers for the specified company ID.

### Response

- HTTP Status Code: 200 (OK)
- Body:

```json


[
  {
    "id": 1,
    "document": "12345678901",
    "documentType": "CPF",
    "name": "John Doe",
    "email": "johndoe@example.com",
    "address": {
      "zipCode": "12345-678",
      "street": "Supplier Street",
      "number": "789",
      "city": "Supplier City",
      "state": "SU",
      "country": "Supplier Country"
    },
    "birthDate": "1990-01-01",
    "rg": "987654321"
  },
  {
    "id": 2,
    "document": "12345678901234",
    "documentType": "CNPJ",
    "name": "Example Supplier",
    "email": "supplier@example.com",
    "address": {
      "zipCode": "54321-098",
      "street": "Supplier Street",
      "number": "456",
      "city": "Supplier City",
      "state": "SU",
      "country": "Supplier Country"
    }
  }
]
```

## Get All Companies

**Endpoint:** `GET /`

Retrieves a list of all companies.

### Response

- HTTP Status Code: 200 (OK)
- Body:

```json
[
  {
    "id": 1,
    "cnpj": "12345678000100",
    "tradeName": "Example Company",
    "address": {
      "zipCode": "12345-678",
      "street": "Example Street",
      "number": "123",
      "city": "Example City",
      "state": "EX",
      "country": "Example Country"
    }
  },
  {
    "id": 2,
    "cnpj": "98765432100123",
    "tradeName": "Another Company",
    "address": {
      "zipCode": "98765-432",
      "street": "Another Street",
      "number": "456",
      "city": "Another City",
      "state": "AN",
      "country": "Another Country"
    }
  }
]
```

## Delete Company

**Endpoint:** `DELETE /{id}`

Deletes a company with the specified ID.

### Response

- HTTP Status Code: 200 (OK)

## Add Supplier to Company

**Endpoint:** `POST /{companyId}/supplier`

Adds a supplier to the specified company.

### Request Body

```json
{
  "supplierId": 1
}
```

### Response

- HTTP Status Code: 200 (OK)

## Update Supplier

**Endpoint:** `PUT /{companyId}/supplier/{supplierId}`

Updates the information of a supplier belonging to a company.

### Request Body

```json
{
  "document": "12345678901",
  "documentType": "CPF",
  "name": "John Doe",
  "email": "johndoe@example.com",
  "address": {
    "zipCode": "12345-678",
    "street": "Updated Street",
    "number": "789",
    "city": "Updated City",
    "state": "UC",
    "country": "Updated Country"
  },
  "birthDate": "1990-01-01",
  "rg": "987654321"
}
```

### Response

- HTTP Status Code: 200 (OK)
- Body:

```json
{
  "id": 1,
  "document": "12345678901",
  "documentType": "CPF",
  "name": "John Doe",
  "email": "johndoe@example

.com",
  "address": {
    "zipCode": "12345-678",
    "street": "Updated Street",
    "number": "789",
    "city": "Updated City",
    "state": "UC",
    "country": "Updated Country"
  },
  "birthDate": "1990-01-01",
  "rg": "987654321"
}
```