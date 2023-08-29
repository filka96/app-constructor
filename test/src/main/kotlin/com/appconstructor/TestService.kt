package com.appconstructor

import io.ktor.server.plugins.NotFoundException
import kotlinx.uuid.UUID

class TestService(private val testRepository: TestRepository) {
  fun create(entity: TestEntity): TestEntity {
    return testRepository.create(entity)
  }

  fun readById(id: UUID): TestEntity {
    return testRepository.readById(id)
           ?: throw NotFoundException()
  }

  fun readAll(): List<TestEntity> {
    return testRepository.readAll()
  }

  fun update(id: UUID, entity: TestEntity): TestEntity {
    return testRepository.update(id, entity)
  }

  fun deleteById(id: UUID) {
    testRepository.deleteById(id)
  }

  fun delete(entity: TestEntity) {
    testRepository.delete(entity)
  }
}
