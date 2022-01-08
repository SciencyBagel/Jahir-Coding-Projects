/*
 * CS3375 Computer Architecture
 * Course Project
 * Cache Simulator Design and Development
 * FALL 2017
 * By Yong Chen
 * Modified by Jahir F. Montes
 */

/*
* COMMAND LINE ARGUMENTS
* 
* <direct> <trace file name> <n-associative>
* 
* <direct> -> direct || fully-associative || n-way
* <trace file name> -> path of trace file
* <n-associative> -> WAY_SIZE. To be used with "n-way" argument.
* 
*/

#include <stdio.h>
#include <stdint.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>
#include <time.h>
#include "cachesim.h"

int main(int argc, char *argv[])
{
  if (argc < 3)
  {
      printf("Usage: %s <direct> <trace file name> <n-associative>\n", argv[0]);
      return 1;
  }

  char* trace_file_name = argv[2];
  char mem_request[20];
  uint64_t address;
  FILE *fp;

  /* Opening the memory trace file */
  fp = fopen(trace_file_name, "r");

  //direct-mapped
  if (strncmp(argv[1], "direct", strlen("direct")) == 0)
  {
    struct direct_mapped_cache d_cache;

    /* Initialization */
    for (int i=0; i<NUM_BLOCKS; i++) {
        d_cache.valid_field[i] = 0;
        d_cache.dirty_field[i] = 0;
        d_cache.tag_field[i] = 0;
    }
    d_cache.hits = 0;
    d_cache.misses = 0;

    /* Simulating direct-mapped d_cache */
    /* Read the memory request address and access the d_cache */

    int cache_accesses = 0;
    while (fgets(mem_request, 20, fp)!= NULL)
    {
      address = convert_address(mem_request);
      direct_mapped_cache_access(&d_cache, address);
      cache_accesses++;
    }

    //calculate hit and miss Rate
    double cache_miss_rate = (double)d_cache.misses / cache_accesses;
    double cache_hit_rate = (double)d_cache.hits / cache_accesses;

    /*Print out the results*/
    printf("\n==================================\n");
    printf("Cache type:    Direct-Mapped Cache\n");
    printf("==================================\n");
    printf("Cache Hits:       %d\n", d_cache.hits);
    printf("Cache Misses:     %d\n", d_cache.misses);
    printf("Cache Miss Rate:  %.2f\n", cache_miss_rate);
    printf("Cache Hit Rate:   %.2f\n", cache_hit_rate);
    printf("Cache Accesses:   %d\n", cache_accesses);
    printf("\n");
  }

  //fully-associative
  else if (strncmp(argv[1], "fully-associative", strlen("fully-associative")) == 0)
  {
    WAY_SIZE = NUM_BLOCKS;
    NUM_SETS = NUM_BLOCKS / WAY_SIZE;

    struct n_way_associative_cache n_cache;
    int n = WAY_SIZE;

    /* Initialization */
    for (int i=0; i<NUM_BLOCKS; i++)
    {
      n_cache.valid_field[i] = 0;
      n_cache.dirty_field[i] = 0;
      n_cache.tag_field[i] = 0;

      n_cache.index_field[i] = 0;
    }
    n_cache.hits = 0;
    n_cache.misses = 0;

    /* Simulating fully-associative n_cache */
    int cache_accesses = 0;
    while (fgets(mem_request, 20, fp) != NULL)
    {
      address = convert_address(mem_request);
      n_way_associative_cache_access(&n_cache, address, WAY_SIZE);
      cache_accesses++;
    }

    //calculate hit and miss Rate
    double cache_miss_rate = (double)n_cache.misses / cache_accesses;
    double cache_hit_rate = (double)n_cache.hits / cache_accesses;

    /*Print out the results*/
    printf("\n==================================\n");
    printf("Cache type:    Fully-Associative Cache\n");
    printf("==================================\n");
    printf("Cache Hits:       %d\n", n_cache.hits);
    printf("Cache Misses:     %d\n", n_cache.misses);
    printf("Cache Miss Rate:  %.2f\n", cache_miss_rate);
    printf("Cache Hit Rate:   %.2f\n", cache_hit_rate);
    printf("Cache Accesses:   %d\n", cache_accesses);
    printf("\n");
  }

  //n-way associative
  else if(strncmp(argv[1], "n-way", strlen("n-way")) == 0)
  {
    WAY_SIZE = atoi(argv[3]);
    NUM_SETS = NUM_BLOCKS / WAY_SIZE;

    struct n_way_associative_cache n_cache;

    /* Initialization */
    for (int i=0; i<NUM_BLOCKS; i++)
    {
      n_cache.valid_field[i] = 0;
      n_cache.dirty_field[i] = 0;
      n_cache.tag_field[i] = 0;

      n_cache.index_field[i] = 0;
    }
    for (int i=0; i < NUM_BLOCKS; i++)
    {
      n_cache.index_field[i] = i % NUM_SETS;
    }
    n_cache.hits = 0;
    n_cache.misses = 0;

    /* Simulating n-way associative n_cache */
    int cache_accesses = 0;
    while (fgets(mem_request, 20, fp) != NULL)
    {
      address = convert_address(mem_request);
      n_way_associative_cache_access(&n_cache, address, WAY_SIZE);
      cache_accesses++;
    }

    //calculate hit and miss Rate
    double cache_miss_rate = (double)n_cache.misses / cache_accesses;
    double cache_hit_rate = (double)n_cache.hits / cache_accesses;

    /*Print out the results*/
    printf("\n==================================\n");
    printf("Cache type:    %d-Associative Cache\n", WAY_SIZE);
    printf("==================================\n");
    printf("Cache Hits:       %d\n", n_cache.hits);
    printf("Cache Misses:     %d\n", n_cache.misses);
    printf("Cache Miss Rate:  %.2f\n", cache_miss_rate);
    printf("Cache Hit Rate:   %.2f\n", cache_hit_rate);
    printf("Cache Accesses:   %d\n", cache_accesses);
    printf("\n");
  }
  fclose(fp);

  printf("BLOCK SIZE = %d Bytes\n", BLOCK_SIZE);
  printf("%d-WAY\n", WAY_SIZE);
  printf("CACHE SIZE = %d Bytes\n", CACHE_SIZE);
  printf("NUMBER OF BLOCKS = %d\n", NUM_BLOCKS);


  return 0;
}

uint64_t convert_address(char memory_addr[]) /* Converts the physical 32-bit address in the trace file to the "binary" (a uint64 that can have bitwise operations on it) */
{
    uint64_t binary = 0;
    int i = 0;

    while (memory_addr[i] != '\n') {
        if (memory_addr[i] <= '9' && memory_addr[i] >= '0') {
            binary = (binary*16) + (memory_addr[i] - '0'); //left hand side shifts bits to left 4 times (if binary =/= 0). right hand side returns integer
        } else {
            if(memory_addr[i] == 'a' || memory_addr[i] == 'A') {
                binary = (binary*16) + 10;
            }
            if(memory_addr[i] == 'b' || memory_addr[i] == 'B') {
                binary = (binary*16) + 11;
            }
            if(memory_addr[i] == 'c' || memory_addr[i] == 'C') {
                binary = (binary*16) + 12;
            }
            if(memory_addr[i] == 'd' || memory_addr[i] == 'D') {
                binary = (binary*16) + 13;
            }
            if(memory_addr[i] == 'e' || memory_addr[i] == 'E') {
                binary = (binary*16) + 14;
            }
            if(memory_addr[i] == 'f' || memory_addr[i] == 'F') {
                binary = (binary*16) + 15;
            }
        }
        i++;
    }

#ifdef DBG
    printf("\n%s converted to %llu\n", memory_addr, binary);
#endif
    return binary;
}

void direct_mapped_cache_access(struct direct_mapped_cache *d_cache, uint64_t address)
{
    uint64_t block_addr = address >> (unsigned)log2(BLOCK_SIZE);
    uint64_t index = block_addr % NUM_BLOCKS;
    uint64_t tag = block_addr >> (unsigned)log2(NUM_BLOCKS);

    #ifdef DBG
        printf("Memory address: %llu, Block address: %llu, Index: %llu, Tag: %llu\n", address, block_addr, index, tag);
    #endif

    if (d_cache->valid_field[index] && d_cache->tag_field[index] == tag) { /* Cache hit */
        d_cache->hits += 1;

        #ifdef DBG
                printf("Hit!...Tag Found = %llu\n\n", d_cache->tag_field[index]);
        #endif
    } else {
        /* Cache miss */
        d_cache->misses += 1;

        #ifdef DBG
                printf("Miss!...Tag Found = %llu\n\n", d_cache->tag_field[index]);
        #endif

        if (d_cache->valid_field[index] && d_cache->dirty_field[index]) {
            /* Write the d_cache block back to memory */
        }
        d_cache->tag_field[index] = tag;
        d_cache->valid_field[index] = 1;
        d_cache->dirty_field[index] = 0;
    }
}

void n_way_associative_cache_access(struct n_way_associative_cache *n_cache, uint64_t address, int n)
{
  uint64_t block_addr = address >> (unsigned)log2(BLOCK_SIZE); //the block address of the memory byte address
  uint64_t set_index = block_addr % NUM_SETS; //selects set
  //uint64_t block_index = block_addr % NUM_BLOCKS;
  uint64_t block_iterator = set_index * n; //selects cache block index 
  uint64_t tag = block_addr >> (unsigned)log2(NUM_BLOCKS);

  int hit = 0;

  #ifdef DBG
    printf("Memory address: %llu, Block address: %llu, Index: %llu, Tag: %llu\n", address, block_addr, set_index, tag);
  #endif

  //traverse SET to search for hits
  for (int i = 0; i < n; i++)
  {
    if (n_cache->valid_field[block_iterator] && n_cache->tag_field[block_iterator] == tag)
    {
      //cache hit
      n_cache->hits += 1;
      hit = 1;
      #ifdef DBG
        printf("Hit!...Tag Found = %llu\n\n", n_cache->tag_field[block_iterator]);
      #endif
      break;
    }
    block_iterator++;
  }

  /* Check if hit or miss */

  if (hit == 0)
  {
    /* Cache miss */
    n_cache->misses += 1;
    #ifdef DBG
      printf("Miss!...Tag Found = %llu\n", n_cache->tag_field[block_iterator]);
    #endif

    /* Choose block to replace */
    block_iterator = set_index * n; //set block index to beginning of set
    int valid_found = 0;

    //check for any valid bits == 0
    for (int i = 0; i < n; i++)
    {
      if (n_cache->valid_field[block_iterator] == 0)
      {
        n_cache->tag_field[block_iterator] = tag;
        n_cache->valid_field[block_iterator] = 1;
        n_cache->dirty_field[block_iterator] = 0;

        valid_found = 1;
        break;
      }
      block_iterator++;
    }

    //check if replaced invalid block
    if (valid_found == 0)
    {
      //invalid block was not found to replace, choose a block at random
      time_t t;
      srand((unsigned) time(&t));
      int random_number = rand() % n; //return random number from 0 to n-1

      block_iterator = set_index * n; //set block index to beginning of set
      block_iterator = block_iterator + random_number; //set block index to random position within set, that is the one we replace

      n_cache->tag_field[block_iterator] = tag;
      n_cache->valid_field[block_iterator] = 1;
      n_cache->dirty_field[block_iterator] = 0;


        #ifdef DBG
            printf("block replaced at block_index = %llu, set_index = %llu\n", block_iterator, set_index);
        #endif

    }

    /* Write back to memory if needed before overwriting */
    if (n_cache->valid_field[block_iterator] && n_cache->dirty_field[block_iterator])
    {
        /* Write the n_cache block back to memory */
    }
  }
}
