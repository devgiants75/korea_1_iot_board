import React, { useEffect, useState } from 'react'
import Pagination from '../../components/Pagination'
import axios from 'axios';

interface Post {
  id: number;
  title: string;
  content: string;
  author: string;
}

export default function Board() {
  const [posts, setPosts] = useState<Post[]>([]);
  const [currentPage, setCurrentPage] = useState<number>(0);
  const [totalPages, setTotalPages] = useState<number>(1);

  const fetchPosts = async (page: number) => {
    const response = await axios.get(`http://localhost:8080/api/v1/posts?page=${page}&size=5`);
    const data = response.data.data;
    setPosts(data.content);
    setTotalPages(data.totalPages);
  }

  useEffect(() => {
    fetchPosts(currentPage);
  }, [currentPage]);

  const handlePageClick = (page: number) => {
    setCurrentPage(page);
  }

  const handlePreSectionClick = () => {
    if (currentPage > 0) {
      setCurrentPage(currentPage - 1);
    }
  }

  const handleNextSectionClick = () => {
    if (currentPage < totalPages - 1) {
      setCurrentPage(currentPage + 1);
    }
  }

  return (
    <div>
      게시판 목록 화면

      <h2>게시판 목록</h2>
      <ul>
        {posts.map((post) => (
          <li key={post.id}>{post.title}</li>
        ))}
      </ul>

      <Pagination 
        pageList={Array.from(Array(totalPages).keys())}
        currentPage={currentPage}
        handlePageClick={handlePageClick}
        handlePreSectionClick={handlePreSectionClick}
        handleNextSectionClick={handleNextSectionClick}
      />
    </div>
  )
}
